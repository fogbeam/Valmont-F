package valmont;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class StopwordList implements InitializingBean
{
	private List<String> stopwords = new ArrayList<String>();
	
	private String fileName;
	private String fileFormat;
	
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		File stopWordsFile = new File( fileName );
		FileReader stopWordsReader = null; 
		BufferedReader bufferedSWR = null;
		
		try
		{
			stopWordsReader = new FileReader( stopWordsFile );
			bufferedSWR = new BufferedReader( stopWordsReader );
		
			String stopWordsLine = null;
			switch( this.fileFormat )
			{
				case "csv":
					stopWordsLine = bufferedSWR.readLine();
					String[] stopWordsArray = stopWordsLine.split( "," );
					stopwords.addAll( Arrays.asList( stopWordsArray ) );
					break;
				case "linesep":
					while( ( stopWordsLine = bufferedSWR.readLine() ) != null )
					{
						stopwords.add( stopWordsLine.trim() );
					}
					break;
			}
		}
		finally
		{
			if( stopWordsReader != null )
			{
				stopWordsReader.close();
			}
			
			if( bufferedSWR != null )
			{
				bufferedSWR.close();
			}
		}
	}
	
	public List<String> getStopwords() 
	{
		return stopwords;
	}
	
	public void setFileName( final String fileName ) 
	{
		this.fileName = fileName;
	}
	
	public String getFileName() 
	{
		return fileName;
	}
	
	public String getFileFormat() 
	{
		return fileFormat;
	}
	
	public void setFileFormat(String fileFormat) 
	{
		this.fileFormat = fileFormat;
	}
}