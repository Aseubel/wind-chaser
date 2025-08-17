package com.aseubel.elegant.seal;

/**
 * 使用permits关键字列出了允许继承的子类Circle、Rectangle和Triangle
 * @author Aseubel
 * @date 2025/8/17 下午9:19
 */
public sealed class Shape permits Circle, Rectangle, Triangle {
}
