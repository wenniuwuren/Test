package com.wenniuwuren.java.generic;

/**
 * 而show_2 和show_3方法其实是完完全全等效的。意思就是说ClassName<T>中一旦
 * T被指定为Fruit后那么show_1没有前缀<T> 的话，该方法中只能是show_1 (Fruit对象)
 *
 * 而你要是有前缀<T>或<E>的话，那么你就是告诉编译器对它说：这是我新指定的一个类型，
 * 跟ClassName<T>类对象中的T没有半毛钱的关系。也就是说这个show_3中的T和show_2中的
 * E是一个效果，也就是你可以把show_3同等程度地理解为<E> void show_3(E e){~~~~~}
 * @param <T>
 */
public class ClassName<T> {

    void show_1(T t) {
        System.out.println("show_1  " + t.toString());
    }

    <E> void show_2(E e) {
        System.out.println("show_2  " + e.toString());
    }

    <T> void show_3(T t) {
        System.out.println("show_3  " + t.toString());
    }

    public static void main(String[] args) {
        ClassName<Fruit> o = new ClassName<Fruit>();
        Fruit f = new Fruit();
        Apple a = new Apple();
        Person p = new Person();
        System.out.println("show_1 演示________________________");
        o.show_1(f);
        o.show_1(a);
        //楼主把这行代码去掉注释看一下，是不能编译通过的。因为在
        //        ClassName<Fruit>中已经限定了全局的T为Fruit，所以不能再加入Person;
//        o.show_1(p);

        System.out.println("show_2 演示________________________");
        o.show_2(f);
        o.show_2(a);
        o.show_2(p);

        System.out.println("show_3 演示________________________");
        o.show_3(f);
        o.show_3(a);
        o.show_3(p);

    }

}

    class Fruit {
        public String toString() {
            return "Fruit";
        }
    }

    class Apple extends Fruit {
        public String toString() {
            return "Apple";
        }
    }

    class Person {
        public String toString() {
            return "Person";
        }
    }

