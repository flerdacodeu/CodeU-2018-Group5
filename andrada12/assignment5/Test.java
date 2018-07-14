/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import package2.*;
/**
 *
 * @author Vali
 */class Alpha {
 public static String s = " ";
 protected Alpha() { s += "alpha "; }
 
 public void met(){
     s = "a";
 }
 }
 class SubAlpha extends Alpha {
    private SubAlpha() { s += "sub "; }
 }
 class SubSubAlpha extends Alpha {
     
    private SubSubAlpha() { s += "subsub "; }
    public void met2(){s+="a";}
    public static void main(String[] args) {
    new SubSubAlpha();
    System.out.println(s);
    }
 }



class MyException extends ArithmeticException {
    public MyException(){
        super();
    }

}
class Houdini {
 public static void main(String[] args) {
    try {
 throw new Error();
 }
 catch (Error e) {
 try { throw new RuntimeException(); }
 catch (Throwable t) { }
 }
 System.out.println("oioioi");
    }
    
}


class Bird {
  

public Bird() { 
    System.out.print("b2 ");
    }

{ System.out.print("b1 ");}
}
class Raptor extends Bird {
    static { System.out.print("r1 "); }
    
    public Raptor() { System.out.print("r2 "); }
    { System.out.print("r3 "); }
    {System.out.print("r5 ");}
    static { System.out.print("r4 "); }
}
class Hawk extends Raptor {
   public Hawk() { System.out.print("h1 "); }
    final static int  o = 0;
    
 
   {System.out.print("h2 "); }
    
    public static void main(String[] args) {
    System.out.print("pre ");
    new Hawk();
    System.out.println("hawk ");
    
    
    }
}

interface Vessel { }
 interface Toy { }
 class Boat implements Vessel { }
 class Speedboat extends Boat implements Toy { }
 class Tree {
     
      public class subT{ public int jo = 0;}
     
     public void m(){
         subT k = new subT();
     }
     
 public static void main(String[] args) {
 

 
 String s = "0";
 Boat b = new Boat();
 Boat b2 = new Speedboat();
 Speedboat s2 = new Speedboat();
 if((b instanceof Vessel) && (b2 instanceof Toy)) s += "1";
 if((s2 instanceof Vessel) && (s2 instanceof Toy)) s += "2";
 System.out.println(s);
 List<List<Integer>> table = new ArrayList<List<Integer>>();
for (int i = 0; i <= 10; i++) {
List<Integer> row = new ArrayList<Integer>();
for (int j = 0; j <= 10; j++)
row.add(i * j);
table.add(row);
}
for (List<Integer> row : table)
System.out.println(row);
 }
}

class A { 
    void m() { System.out.println("outer");}
}
 class TestInners {
     
     public static void main(String[] args) {
        new TestInners().go();
    }
    void go() {
        
        class A { 
            void m() {System.out.println("inner"); } 
        }
        new A().m();
    }
    class A { 
        void m() {
        System.out.println("middle"); }
    }
 }

 class AndOver {
 public static void main(String[] args) {
 List g = new ArrayList();
 g.add(new Gaited("Eyra"));
 g.add(new Gaited("Vafi"));
 g.add(new Gaited("Andi"));
 Iterator i2 = g.iterator();
 while(i2.hasNext()) {
     Gaited go = (Gaited) i2.next();
 System.out.print(go.name + " ");
 
 int count = 0;
 outer:
 for(int x = 0; x < 5; x++) {
 middle:
 for(int y = 0; y < 5; y++) {
 if(y == 1) continue middle;
 if(y == 3) break middle;
 count++;
 }
 if(x > 2) continue outer; 
 count = count + 10;
 }
 System.out.println("count: " + count);
 
 
 boolean[] ba = {true, false};
 short[][] gr = {{1,2}, {3,4}};
 int i = 0;
 for( ; i < 10; ) i++;
 for(short s: gr[0]) ;
 for(int j = 0, k = 10; k > j; ++j, k--) ;
 for(int j = 0; j < 3; System.out.println(j++)) ;
 for(Boolean b: ba) ;
 
 
 } } }
 class Gaited {
 String name;
 Gaited(String n) { name = n; }
 }


class X {
    void pr (X ob) {
        System.out.print("A");
    }
    void pr (Y ob) {
        System.out.print("B");
    }
}
class Y extends X {
    void pr (Y ob) {
        System.out.print("D");
    }
}

class D {
    int method(){return 1;}
}
class E extends D{
    int method () {return 2;}
}
class F extends E{
    String name;
    int fun(D d ){return d.method();}
    ArrayList<Integer> go(){
        Object o = (Object) new E();
        E e = (E) o;
        e.method();
        ArrayList<Integer> l = new ArrayList<Integer>();
        return l;
        
        
    }
    
    void schimba (F obj) {obj.name = "Andrada";}
}

class Outer{
    public void outerMethod(){
        new Inner();
    }
    
     class Inner{}
    
    public static void main(String[] args){
        Outer o = new Outer();
        
    }
}

class Animal{
    Number noise(){return 2;}
}

class Dog extends Animal{
    
   // String noise(){return "bark";}
    
}

class Cat extends Animal{
    String rasa;
    static void a(){}
    Integer noise(){a();return 1;}
    void m(){};
}

class P {

        private static int count = 0;
        public P(){count++;}
        public int getCount(){return count;}
        
        public static void main(String[] args){
            P p1 = new P();
            P p2 = new P();
            P p3 = new P();
            Animal animal = new Cat();
            Cat cat = (Cat) animal;
      //      System.out.println(animal.m());
        }
}

class Test2{
   public static void main(String[] args){
       F f = new F();
       f.name = "Vali";
       E e = new E();
       //f.schimba(f);
       new bar();
   }
}


class Moo{
    static int x;
    Moo() {doSth();x++;}
    void doSth(){x++;}
}

class bar extends Moo {
        bar() {System.out.println(x);}
        void doSth() {x+=2;}
}


class Student implements Comparable<Student>{

    @Override
    public int compareTo(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

class somComparator implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class TestXY {
    public static void main (String args[]) {
        X o1=new X(), o2 = new Y();
        Y o3 = new Y();
        o1.pr(new X());
        o1.pr(new Y());
        o2.pr(new X());
        o2.pr(new Y());
        o3.pr(new X());
        o3.pr(new Y());
    }
}

 

 class Argue {
 static boolean b;
 static int x = 0;
 public static void main(String[] args) {
    
    int guess = (int)(Math.random() * 5);
 if(guess < 0) assert false;
 assert b = true;
// assert x = 0;
 assert x == 0;
 } }

class MyThread extends Thread {
MyThread() {
System.out.print(" MyThread");
}
public void run() { System.out.print(" bar"); }
public void run(String s) { System.out.print("baz"); }
}
class TestThreads {
public static void main (String [] args) {
Thread t = new MyThread() {
public void run() { System.out.print(" foo"); }
};
t.start();
} }
public class Test extends Foo{

    /**
     * @param args the command line arguments
     */
    public int j;
    public Test(){
        
        j = 11;
        Tree t = new Tree();
        Tree.subT j;
    
    }
    
    /*public static void main(String[] args) {
        Foo f = new Foo();
        Test t = new Test();
        String[] v= new String[10];
        String s = "aaa";
        
        
         for(int _x = 0; _x <=3; _x++);
        // int x = 2/0;
         System.out.print(t.b + " "+ t.c+ " " + t.j);
         Boolean [] ba[];
    }*/
    
}
