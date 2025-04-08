package dev.pimous.l2s3sdn.tp3;

class Main{

	public static void main(String[] args){
		Cafe c1 = new Cafe(System.out, 
			"Xibitol PUB", 2, 60
		);
		Cafe c2 = new Cafe(System.out,
			"Lenny Bar", 6, 150
		);

		c1.open(28);
		c2.open(90);

		c1.handle(0);
		c2.handle(0);

		c1.close();
		c2.close();
	}
}