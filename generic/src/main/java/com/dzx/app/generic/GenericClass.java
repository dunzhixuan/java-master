package com.dzx.app.generic;

import java.io.Serializable;

import sun.applet.Main;

public class GenericClass {

		public static void main(String[] args){
				//1、泛型类
				setPerson();
				//2、泛型接口
				setTeacher();
				//3、泛型通配符
				wildCard();
				//4、泛型方法 -- 泛型方法中的注意点看GenericTest // 类中的泛型方法请看GenericFruit类
				try {
						Object obj = genericTeacher(Main.class,new Person<Object>(12));
						System.out.println(obj);
				} catch (Exception e) {
						e.printStackTrace();
				}
				//5、泛型方法和可变参数
				printMsg("111",222,"aaa",333.33);
				//6、静态方法的泛型
				show(Integer.class);
				//7、泛型方法总结：
				/* 无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化，那么就应该使用泛型方法。
				另外对于一个static的方法而已，无法访问泛型类型的参数。所以如果static方法要使用泛型能力，就必须使其成为泛型方法。*/
				//8、泛型的上下界：在使用泛型的时候，我们还可以为传入的泛型类型实参进行上下边界的限制，如：类型实参只准传入某种类型的父类或某种类型的子类。
				showKeyValue1(new Person<>(1.0));
				showKeyValue1(new Person<>(99));

				showKeyValue2(new Person<>(100));
				showKeyValue2(new Person<Number>(1.0));
				showKeyValue2(new Person<Serializable>(1.0));
		}

		private static void setPerson() {
				Person<Integer> xiaowang = new Person<>(10);
				Person<String> xiaolu = new Person<>("20");
				System.out.println(xiaolu.getAge() + xiaowang.getAge().toString());

				//定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，
				//此时泛型才会起到本应起到的限制作用。如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
				Person xiaoli = new Person(30);
				System.out.println(xiaolu.getAge() + xiaowang.getAge().toString() + xiaoli.getAge());
		}

		private static void setTeacher(){
				Teacher<String> teacherWang = new Teacher<>();
				teacherWang.next();

				MathTeacher mathTeacherLi = new MathTeacher();
				mathTeacherLi.next();
		}

		private static void wildCard(){
				Person<Integer> xiaowang = new Person<>(10);
				Person<String> xiaolu = new Person<>("20");
				eat(xiaolu);
		}

		//类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。
		// 重要说三遍！此处’？’是类型实参，而不是类型形参 ！ 此处’？’是类型实参，而不是类型形参 ！
		// 再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。是一种真实的类型
		public static void eat(Person<?> person){
				person.getAge();
		}

		//泛型方法: <T>表示泛型方法、<T>后面的T表示返回值，如果没有则是void
		/**
		 * @param tClass 传入的泛型实参
		 * @return T 返回值为T类型
		 * 1) public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
		 * 2) 只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法
		 * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T
		 * 4）与泛型类的定义一样，此处T可以随便写为任意标识，如T、E、K、V等形式的参数常用于表示泛型
		 * 5) <T>可以有多个泛型参数如<T,K,V>
		 * */
		public static <T,K,V> T genericTeacher(Class<T> tClass,Person<K> name) throws Exception{
				T instance = tClass.newInstance();
				Person<K> person = name;
				return instance;
		}

		//泛型方法和可变参数
		public static <T> void printMsg( T... args){
				for(T t : args){
						System.out.println("泛型测试t is " + t);
				}
		}

		/**
		 * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
		 * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
		 * 如：public static void show(T t){..},此时编译器会提示错误信息：
		 "StaticGenerator cannot be refrenced from static context"
		 */
		public static <T> void show(T t){

		}

		//泛型的上界
		//为泛型添加上边界，即传入的类型实参必须是指定类型的子类型
		public static void showKeyValue1(Person<? extends Number> obj){
				System.out.println("泛型测试key value is " + obj.getAge());
		}

		//泛型的下界
		//为泛型添加下边界，即传入的类型实参必须是指定类型的子类型
		public static void showKeyValue2(Person<? super Integer> obj){
				System.out.println("泛型测试key value is " + obj.getAge());
		}

}
