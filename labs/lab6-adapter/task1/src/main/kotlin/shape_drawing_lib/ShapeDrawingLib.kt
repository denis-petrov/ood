package shape_drawing_lib

import graphics_lib.ICanvas

open class Point(
    var x: Int,
    var y: Int
) {}

interface ICanvasDrawable {
    fun draw(canvas: ICanvas)
}

class Triangle(
    private val p1: Point,
    private val p2: Point,
    private val p3: Point
) : ICanvasDrawable {

    override fun draw(canvas: ICanvas) {
        canvas.moveTo(p1.x, p1.y)
        canvas.lineTo(p2.x, p2.y)

        canvas.moveTo(p2.x, p2.y)
        canvas.lineTo(p3.x, p3.y)

        canvas.moveTo(p3.x, p3.y)
        canvas.lineTo(p1.x, p1.y)
    }
}

class Rectangle(
    private val leftTop: Point,
    private val width: Int,
    private val height: Int
) : ICanvasDrawable {

    override fun draw(canvas: ICanvas) {
        canvas.moveTo(leftTop.x, leftTop.y)
        canvas.lineTo(leftTop.x + width, leftTop.y)

        canvas.moveTo(leftTop.x + width, leftTop.y)
        canvas.lineTo(leftTop.x + width, leftTop.y - height)

        canvas.moveTo(leftTop.x + width, leftTop.y - height)
        canvas.lineTo(leftTop.x, leftTop.y - height)

        canvas.moveTo(leftTop.x, leftTop.y - height)
        canvas.lineTo(leftTop.x, leftTop.y)
    }
}

class CanvasPainter(private val canvas: ICanvas) {

    fun draw(drawable: ICanvasDrawable) {
        drawable.draw(canvas)
    }
}

