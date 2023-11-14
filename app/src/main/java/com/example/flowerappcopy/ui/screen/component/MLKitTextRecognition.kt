package com.example.flowerappcopy.ui.screen.component



import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.flowerappcopy.GraphicOverlay
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import java.util.concurrent.Executors


@Composable
fun MLKitTextRecognition() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val extractedText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextRecognitionView(
            context = context,
            lifecycleOwner = lifecycleOwner,
            extractedText = extractedText
        )
        Text(
            text = extractedText.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun TextRecognitionView(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    extractedText: MutableState<String>
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProvider = cameraProviderFuture.get()
    val textRecognizer = remember { TextRecognition.getClient() }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    Box {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
            ,
            factory = { ctx ->
                val previewView = PreviewView(ctx)

                cameraProviderFuture.addListener({
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .apply {
                            setAnalyzer(
                                cameraExecutor,
                                ObjectDetectorImageAnalyzer(textRecognizer, extractedText , preview)
                            )
                        }
                    val cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            imageAnalysis,
                            preview
                        )
                }, executor)

                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                previewView
            }
        )
       // DrawCropScan()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = { Toast.makeText(context, "Back Clicked", Toast.LENGTH_SHORT).show() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
        }
    }
}


class ObjectDetectorImageAnalyzer(
    private val textRecognizer: TextRecognizer,
    private val extractedText: MutableState<String>,
    private val preview: Preview?
): ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            textRecognizer.process(image)
                .addOnCompleteListener { textoReconocido ->
                    if (textoReconocido.isSuccessful) {
                       // Log.d("Click", it. )

                        extractedText.value = textoReconocido.result?.text ?: ""

                        val ttt = textoReconocido.result
                        for (block in ttt.textBlocks) {
                            val blockText = block.text
                            val lineText = block.lines
                            val blockCornerPoints = block.cornerPoints
                            val blockFrame = block.boundingBox
                             Log.d("Click a1", blockText )
                             Log.d("Click a2", blockCornerPoints.toString() )
                             Log.d("Click a3", blockFrame.toString() )
                             Log.d("Click a3", "\n" )


                            for (line in block.lines) {
                                val lineText = line.text
                                val lineCornerPoints = line.cornerPoints
                                val lineFrame = line.boundingBox
                                Log.d("Click b1", lineText )
                                Log.d("Click b2", lineCornerPoints.toString() )
                                Log.d("Click b3", lineFrame.toString() )
                                Log.d("Click b3", "\n" )

                                for (element in line.elements) {
                                    val elementText = element.text
                                    val elementCornerPoints = element.cornerPoints
                                    val elementFrame = element.boundingBox
                                    Log.d("Click c1", elementText )
                                    Log.d("Click c2", elementCornerPoints.toString() )
                                    Log.d("Click c3", elementFrame.toString() )
                                    Log.d("Click c3", "\n" )


                                   

                                  //  RecognizedVin(elementText, elementFrame )
                                    val textGraphic: GraphicOverlay.Graphic
                                  ///  preview.showHandle(element.text, element.boundingBox)


                                }
                            }
                        }
                    }
                    imageProxy.close()
                }
                .addOnFailureListener {
                    Log.d("click", "Failed to process the image")
                    it.printStackTrace()
                    imageProxy.close()
                }
        }
    }
}


fun Bitmap.drawWithRectangle(objects: List<DetectedObject>): Bitmap?{
    val bitmap = copy(config, true)
    val canvas = Canvas(bitmap)
    var thisLabel = 0
    for (obj in objects){
        thisLabel++
        val bounds = obj.boundingBox
        Paint().apply {
            color = Color.Red.toArgb()
            style = Paint.Style.STROKE
            textSize = 32.0f
            strokeWidth = 4.0f
            isAntiAlias = true
            // draw rectangle on canvas
            canvas.drawRect(
                bounds,
                this
            )
            canvas.drawText(thisLabel.toString(),
                bounds.left.toFloat(),
                bounds.top.toFloat(), this )
        }

    }
    return bitmap
}

data class RecognizedVin(
    val text: String,
    val boundingBox: android.graphics.Rect?,
)

val cropTopLeftScale: Offset = Offset(x = 0.025f, y = 0.3f)
val cropSizeScale: Size = Size(width = 0.95f, height = 0.1f)
@Composable
fun DrawCropScan(
    topLeftScale: Offset = cropTopLeftScale,
    sizeScale: Size = cropSizeScale,
    color: Color = MaterialTheme.colors.primary,
) {

    var lineBottomY by remember { mutableStateOf(0f) }

    var isAnimated by remember { mutableStateOf(true) }

    val lineYAnimation by animateFloatAsState(
        targetValue = if (isAnimated) 0f else lineBottomY,
        animationSpec = infiniteRepeatable(animation = TweenSpec(durationMillis = 1500))
    )

    LaunchedEffect(true) {
        isAnimated = !isAnimated
    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned {
        }
    ) {

        val paint = Paint()
        paint.apply {
            isAntiAlias = true
            textSize = 24.sp.toPx()
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        // 绘制背景
        drawRect(Color.Transparent.copy(alpha = 0.1f))

        // 扫描框 高度、宽度
        var height = size.height * sizeScale.height
        var with = size.width * sizeScale.width

        // square 方形
        if(sizeScale.height == 0f ){
            height = with
        }
        if(sizeScale.width == 0f ){
            with = height
        }


        val topLeft = Offset(x = size.width * topLeftScale.x, y = size.height * topLeftScale.y)


        // 扫描框 矩形
        val rectF = Rect(offset = topLeft, size = Size(with, height))

//        Log.d("rectF", " width-height: ${rectF.width} * ${rectF.height}")
//        Log.d("rectF", "$rectF")
//        Log.d("size", "${size.toRect()}")


        drawRoundRect(
            color = Color.Transparent,
            topLeft = rectF.topLeft, size = rectF.size,
            blendMode = BlendMode.Clear
        )

        // 扫描线 可到达的最大位置
        lineBottomY = height - 5.dp.toPx()

        val padding = 10.dp.toPx()
        // 扫描线
        val rectLine = Rect(
            offset = topLeft.plus(Offset(x = padding, y = lineYAnimation)),
            size = Size(with - 2 * padding, 3.dp.toPx())
        )

        // 画扫描线
        drawOval(color, rectLine.topLeft, rectLine.size)

        // 边框
        val lineWith = 3.dp.toPx()
        val lineLength = 12.dp.toPx()

        val lSizeH = Size(lineLength, lineWith)
        val lSizeV = Size(lineWith, lineLength)

        val path = Path()
        // 左上角
        path.addRect(Rect(offset = rectF.topLeft, lSizeH))
        path.addRect(Rect(offset = rectF.topLeft, lSizeV))

        // 左下角
        path.addRect(Rect(offset = rectF.bottomLeft.minus(Offset(x = 0f, y = lineWith)), lSizeH))
        path.addRect(Rect(offset = rectF.bottomLeft.minus(Offset(x = 0f, y = lineLength)), lSizeV))
        // 右上角
        path.addRect(Rect(offset = rectF.topRight.minus(Offset(x = lineLength, y = 0f)), lSizeH))
        path.addRect(Rect(offset = rectF.topRight.minus(Offset(x = lineWith, y = 0f)), lSizeV))
        // 右下角
        path.addRect(
            Rect(offset = rectF.bottomRight.minus(Offset(x = lineLength, y = lineWith)), lSizeH)
        )
        path.addRect(
            Rect(offset = rectF.bottomRight.minus(Offset(x = lineWith, y = lineLength)), lSizeV)
        )

        drawPath(path = path, color = Color.White)
    }
}


class GraphicOverlay(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "GraphicOverlay"
    private val lock = Any()

    private val graphics = mutableListOf<BoxData>()

    private var rectPaintStyle: Paint? = null

    private val rectPaint = Paint().apply {
        color =  Color.Yellow.toArgb()
        style = Paint.Style.STROKE
        strokeWidth = 2 * resources.displayMetrics.density
    }

    private val rectTransparent = Paint().apply {
        color = Color.Transparent.toArgb()
        style = Paint.Style.STROKE
        strokeWidth = 2 * resources.displayMetrics.density
    }

    private val textBackgroundPaint = Paint().apply {
        color = Color.Blue.toArgb()
        style = Paint.Style.FILL
    }

    private val textPaint = Paint().apply {
        color = Color.White.toArgb()
        textSize = 20 * resources.displayMetrics.density
    }

    private val rect = RectF()
    private val offset = resources.displayMetrics.density * 8
    private val round = resources.displayMetrics.density * 4

    private var scale: Float = 1f

    private var xOffset: Float = 0f
    private var yOffset: Float = 0f

    fun setConfiguration(imageWidth: Int,
                         imageHeight: Int,
                         rectColor: Int = Color.White.toArgb()) {
        setRectPaint(rectColor)
        val overlayRatio = width / height.toFloat()
        val imageRatio = imageWidth / imageHeight.toFloat()

        if (overlayRatio < imageRatio) {
            scale = height / imageHeight.toFloat()

            xOffset = (imageWidth * scale - width) * 0.5f
            yOffset = 0f
        } else {
            scale = width / imageWidth.toFloat()

            xOffset = 0f
            yOffset = (imageHeight * scale - height) * 0.5f
        }
    }

    private fun setRectPaint(rectColor: Int = Color.White.toArgb()){
        rectPaintStyle = Paint().apply{
            color = rectColor
            style = Paint.Style.STROKE
            strokeWidth = 2 * resources.displayMetrics.density
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        synchronized(lock) {
            for (graphic in graphics) {
                rect.set(
                    graphic.boundingBox.left * scale,
                    graphic.boundingBox.top * scale,
                    graphic.boundingBox.right * scale,
                    graphic.boundingBox.bottom * scale
                )

                rect.offset(-xOffset, -yOffset)

                canvas.drawRect(rect, rectPaintStyle!!)

                if (graphic.texts.isNotEmpty()) {
                    canvas.drawRoundRect(
                        rect.left,
                        rect.bottom - offset,
                        rect.left + (offset*2) + (40*20),
                        rect.bottom + (textPaint.textSize * graphic.texts.size) + offset,
                        round,
                        round,
                        textBackgroundPaint
                    )
                    for ((i, text) in graphic.texts.withIndex()){
                        canvas.drawText(
                            "${i}: ${text}",
                            rect.left + offset,
                            rect.bottom + (textPaint.textSize * (i+1)),
                            textPaint
                        )
                    }
                }
            }
        }
    }

    fun set(list: List<BoxData>) {
        synchronized(lock) {
            graphics.clear()
            for (boxData in list) {
                graphics.add(boxData)
            }
        }
        postInvalidate()
    }
}


data class BoxData(
    val texts: List<String>,
    val boundingBox: Rect
)
