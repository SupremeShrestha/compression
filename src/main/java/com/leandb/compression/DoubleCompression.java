package com.leandb.compression;

import java.nio.ByteBuffer;

public class DoubleCompression {

	/*
	 * method to convert doublearray into 
	 */
	public byte[] doubleToBits(double d)
	{
		byte[] bytes = new byte[8];
		ByteBuffer buf = ByteBuffer.wrap(bytes);
		buf.putDouble(d);
		return bytes;
	}
	
	public void byteArrayToBits(byte[] b)
	{
		for (int i = 0; i<b.length;i++)
		{
			byte  bb = b[i];
			System.out.print(ByteUtils.byteToBits((byte) bb)+" ");
		}

	}
	
	
public void loop(double[] d) 
{
	double currentvalue = 0;
	double previousvalue = 0;
	for (int i = 0; i<d.length;i++ ){
		
		
		currentvalue = d[i];
		byte[] b1 =(doubleToBits(currentvalue));
		byte[] b2 =(doubleToBits(previousvalue));
		byte[] xor = computeXOR(b1,b2); 
		
		byteArrayToBits(b1);
		System.out.println();
		byteArrayToBits(b2);
		System.out.println();
		byteArrayToBits(xor);
		System.out.println();
		System.out.print(leadingZeroByteArray(xor));

		System.out.println();
		System.out.print(trailingZeroByteArray(xor));
		System.out.println("\n");
		
		
		
		
		
		

			
		
		
		previousvalue = currentvalue;
		}
	}

public byte[] computeXOR(byte[] b1, byte[] b2) {
	byte[] xor = new byte[8];
	for (int i= 0; i<xor.length;i++)
	{
		xor[i] = (byte) (b1[i] ^ b2[i]);
	}	
	return xor;
}
public int leadingZeroByte(byte b1)
{
	int l = 0;
	while (b1 != 0)
	{
		b1 = (byte) ((b1 & 0xFF) >>> 1);
		l++;	
	}
	return 8-l;
	
}
public int leadingZeroByteArray(byte[] b)
{
	int i = 0;
	int sum = 0;
	int leadingZero;
	do{
		leadingZero = leadingZeroByte(b[i]);
		sum = sum + leadingZero;
		i++;
	} while(i < b.length && leadingZero == 8 );
		
	return sum;
	
}
public int trailingZeroByte(byte b1)
{
	int l = 0;
	while ((b1 & 0x01) == 0 && l <= 7 )
	{
		b1 = (byte) ((b1 & 0xFF) >>> 1);
		l++;	
	}
	return l;
	
}

public int trailingZeroByteArray(byte[] b1)
{
	int i = 7;
	int sum = 0;
	int trailingZero;
	do{
		trailingZero = trailingZeroByte(b1[i]);
		sum = sum + trailingZero;
		i--;
		
	} while(i >= 0  && trailingZero == 8 );
	
	return sum;
	
}

		
}
	
	
	


