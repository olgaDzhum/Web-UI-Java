package ru.geekbrains.lesson4;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@AllArgsConstructor
public class Triangle {

@Getter
@Setter
    private double a;
    private double b;
    private double c;


    public double calculateTriangleSquare ()  {
        if (a<=0 || b<=0 || c<=0) throw new  IllegalArgumentException("Каждое значение стороны должно быть положительным числом.");
        if (c>(a+b) || b>(a+c) || a>(b+c)) throw new IllegalArgumentException("Треугольник с такими параметрами не возможен.");
         double halfPerimeter = (a+b+c)/2;
         return Math.sqrt(halfPerimeter*(halfPerimeter-a)*(halfPerimeter-b)*(halfPerimeter-c));
    }


}


