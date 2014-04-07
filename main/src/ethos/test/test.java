package ethos.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

public class test {

	@Test
	public void test1() throws IOException
	{
		File file = new File("./main/src/ethos/test/S12_VolumeDataTotals.java");
		List<String> lines=FileUtils.readLines(file);
		String scenario="S12_";
		int tcCount=1;
		String line="";
		for(int i=0;i<lines.size();i++)
		{
			if(lines.get(i).contains("@Test"))
				{
					i++;
					lines.set(i,lines.get(i).replace("void ","void "+scenario+(tcCount++)+"_"));
				}
		}
		
		FileUtils.writeLines(file, lines);
	}
}
