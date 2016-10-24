package co.edu.uniquindio.multi.main;

import javax.swing.JOptionPane;

public class App {


	public static void main(String[] args) {
		float a =Float.parseFloat(JOptionPane.showInputDialog("Ingrese el número 1."));
		float b =Float.parseFloat(JOptionPane.showInputDialog("Ingrese el número 2."));
		System.out.println(multi(a,b));
		
	}

	private static float multi(float a, float b) {		
		int x=Float.floatToRawIntBits(a);
		int y=Float.floatToRawIntBits(b);
		byte s1=(byte)((x>>31)&0x01);
		byte s2=(byte)((y>>31)&0x01);
		byte s=(byte)((s1)^(s2));
		int m1=(1<<23) | x&0x7fffff;
		int m2=(1<<23) | y&0x7fffff;
		long m=(((long)m1*(long)m2)>>23)&0x0000000001fffffff;
		byte exp1=(byte)(((x>>23)&0xff)-127);
		byte exp2=(byte)(((y>>23)&0xff)-127);
		byte exp=(byte) (exp1+exp2+127);
		if(((m>>24)&1)==1){
			exp=(byte)(exp+1);
			m=m>>1;
		}
		int zi=(s<<31) | ((exp<<23)&0x7f800000) | (int)(m&0x7fffff);
		float z=Float.intBitsToFloat(zi);
		return z;		
	}
}
