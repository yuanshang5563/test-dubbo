package org.ys.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.junit.Test;

public class TestNIO {
	@Test
	public void testSaveFile(){
		RandomAccessFile raf = null;
		FileChannel fileChannel = null;
		try {
			raf = new RandomAccessFile("D:\\fileTest\\testFile.txt","rw");
			fileChannel = raf.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(2048);
			buffer.put("test！".getBytes("UTF-8"));
			buffer.flip();
			fileChannel.write(buffer,raf.length());
			buffer.compact();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != fileChannel){
					fileChannel.close();
				}
				if(null != raf){
					raf.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testReadFile(){
		long startTime = System.currentTimeMillis();
		RandomAccessFile raf = null;
		FileChannel fileChannel = null;
		try {
			raf = new RandomAccessFile("D:\\fileTest\\testFile.txt","r");
			fileChannel = raf.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(2048);
			int readInt = fileChannel.read(buffer);
			while(readInt != -1){
				buffer.flip();
				String str = new String(buffer.array(),"UTF-8");
				System.out.println(str);
				buffer.compact();
				readInt = fileChannel.read(buffer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != fileChannel){
					fileChannel.close();
				}
				if(null != raf){
					raf.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("----------------nio消耗时间---------------" + (endTime - startTime));
	}
	
	@Test
	public void testReadFile2(){
		long startTime = System.currentTimeMillis();
		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		try {
			fileInputStream = new FileInputStream("D:\\fileTest\\testFile.txt");
			fileChannel = fileInputStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(2048);
			int readInt = fileChannel.read(buffer);
			while(readInt != -1){
				buffer.flip();
				String str = new String(buffer.array(),"UTF-8");
				System.out.println(str);
				buffer.compact();
				readInt = fileChannel.read(buffer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != fileChannel){
					fileChannel.close();
				}
				if(null != fileInputStream){
					fileInputStream.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("----------------nio消耗时间---------------" + (endTime - startTime));
	}	
	
	@Test
	public void testReadFileByIO(){
		long startTime = System.currentTimeMillis();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("D:\\fileTest\\testFile.txt");
			byte[] bytes = new byte[2048];
			int readInt = fileInputStream.read(bytes);
			while(readInt != -1){
				String str = new String(bytes,"UTF-8");
				System.out.println(str);
				readInt = fileInputStream.read(bytes);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != fileInputStream){
					fileInputStream.close();
				}			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("----------------io消耗时间---------------" + (endTime - startTime));
	}	
	
	@Test
	public void testCopyFile(){
		long startTime = System.currentTimeMillis();
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		ReadableByteChannel readableByteChannel = null;
		WritableByteChannel writableByteChannel = null;
		try {
			fileInputStream = new FileInputStream("D:\\fileTest\\testFile.txt");
			fileOutputStream = new FileOutputStream("D:\\fileTest\\testFile2.txt");
			readableByteChannel = fileInputStream.getChannel();
			writableByteChannel = fileOutputStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(2048);
			int readInt = readableByteChannel.read(buffer);
			while(readInt != -1){
				buffer.flip();
				writableByteChannel.write(buffer);
				buffer.compact();
				readInt = readableByteChannel.read(buffer);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != readableByteChannel){
					readableByteChannel.close();
				}
				if(null != writableByteChannel){
					writableByteChannel.close();
				}				
				if(null != fileInputStream){
					fileInputStream.close();
				}			
				if(null != fileOutputStream){
					fileOutputStream.close();
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("----------------nio消耗时间---------------" + (endTime - startTime));
	}
}
