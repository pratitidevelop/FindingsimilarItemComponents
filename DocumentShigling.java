import java.io.*;
import java.util.*;

public class DocumentShigling{
	public static void main(String[] args) throws IOException{
		/*Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader("adventure.txt", charset)) {
    			String line = null;
   			 while ((line = reader.readLine()) != null) {
        				System.out.println(line);
    			}
		}
		 catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}*/

		Hashtable<String, String> h1 = 
              			new Hashtable<String, String>();


		Hashtable<String, String> h2 = 
              			new Hashtable<String, String>();


		Hashtable<String, String> h3 = 
              			new Hashtable<String, String>();
		
		//Hashtable<String, String> h4 = 
              			//new Hashtable<String, String>();
		
		/*String article=" a an the";
		
			while(article!=null){
				StringTokenizer s5= new StringTokenizer(article," ");
				while(s5.hasMoreElements()){
					String w1=(String)s5.nextElement();
					h4.put(w1,w1);
				}
			}*/

		//hashtable h1 contains the adjectives
		try{
			BufferedReader br1 = new BufferedReader(new FileReader("adjectives.txt"));
			String content1=br1.readLine();
			while(content1!=null){
				StringTokenizer s1= new StringTokenizer(content1,"\n");
				while(s1.hasMoreElements()){
					String word=(String)s1.nextElement();
					h1.put(word,word);
				}
				content1=br1.readLine();
			}
		}
		catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

	
		//boolean has=h1.containsKey("dynamic");
		//System.out.println(has+"the element");	


		//hashtable h2 contains the nouns		
		
		try{
			BufferedReader br2 = new BufferedReader(new FileReader("adjectives.txt"));
			String content2=br2.readLine();
			while(content2!=null){
				StringTokenizer s1= new StringTokenizer(content2,"\n");
				while(s1.hasMoreElements()){
					String word=(String)s1.nextElement();
					h2.put(word,word);
				}
				content2=br2.readLine();
			}
		}
		catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}


		//hashtable h3 contains the nouns		
	
		try{
			BufferedReader br3 = new BufferedReader(new FileReader("adjectives.txt"));
			String content3=br3.readLine();
			while(content3!=null){
				StringTokenizer s1= new StringTokenizer(content3,"\n");
				while(s1.hasMoreElements()){
					String word=(String)s1.nextElement();
					h3.put(word,word);
				}
				content3=br3.readLine();
			}
		}
		catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}



		try{		
			File file1= new File("cleanedDocument.txt");
			if(file1.exists())
				file1.createNewFile();


				FileWriter fw= new FileWriter(file1,true);
				BufferedWriter bw = new BufferedWriter(fw);
		
				try{
					BufferedReader br = new BufferedReader(new FileReader("adventure.txt"));
		
					String line=br.readLine();

					while(line!=null){
						StringTokenizer st= new StringTokenizer(line," ");
						while(st.hasMoreElements()){
							String word=(String)st.nextElement();
							word+=" "; 
							if(!h1.containsKey(word))
								bw.write(word);
							else if(!h2.containsKey(word))
								bw.write(word);
							else if(!h3.containsKey(word))
								bw.write(word);
							//else if(!h4.containsKey(word))
								//bw.write(word);
							else
								bw.write(word);
						}					
					//System.out.println();

					line=br.readLine();


				}

				}
		 		catch (IOException x) {
		    			System.err.format("IOException: %s%n", x);
				}
				bw.close();	

		}
		catch(IOException io){
			System.out.println("Exception occured");
			io.printStackTrace();
		}

		//Arraylist of strings to tore the words of the file   ArrayList- aLdata

		 ArrayList<String> aLdata = new ArrayList<String>();



		//see through what is there in the file

		try{
			BufferedReader br = new BufferedReader(new FileReader("cleanedDocument.txt"));
		
			String line=br.readLine();

			while(line!=null){
				StringTokenizer st= new StringTokenizer(line," ");
				while(st.hasMoreElements()){
					String word=(String)st.nextElement();
					aLdata.add(word);					
					//System.out.println(st.nextElement()+" " );
				}
				//System.out.println();
				line=br.readLine();


			}

		}
		 catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

		//convert the arraylist aLdata to the array for the shingles
		
		String arrayData[] = new String[aLdata.size()];
   		arrayData = aLdata.toArray(arrayData);
		/*for(int i=0;i<arrayData.length;i++)
			System.out.println(arrayData[i]);*/
		int k=4;
		//sub-array to create k-shingles say of size 4
		for(int i=0;i<arrayData.length-k;i++){
			for(int j=i;j<i+k;j++){
				System.out.print(arrayData[j]+" ");
			}
			System.out.println();
		}

		int countLines=0;
		try{
			File file1= new File("Shingles.txt");
			if (!file1.exists()) {
				file1.createNewFile();
			}

			String line;
			FileWriter fw = new FileWriter(file1.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<arrayData.length-k;i++){
				line=" ";
			for(int j=i;j<i+k;j++){
				line+=arrayData[j]+" ";
			}
				line+="\n";
				bw.write(line);
				countLines+=1;
			}
			bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}




			boolean charaMatrix[][]= new boolean[arrayData.length][countLines];
			



			try{
				FileInputStream fis = new FileInputStream("Shingles.txt");
				 BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

				int c=0;
				String line1=reader.readLine();
				while(line1 != null){	

				
					StringTokenizer st= new StringTokenizer(line1," ");
					while(st.hasMoreElements()){
							String word=(String)st.nextElement();
							for(int i=0;i<arrayData.length;i++){
								if(arrayData[i]==word){
									charaMatrix[i][c]=true;
								}
								else{
									charaMatrix[i][c]=false;
								}
							}
					}
					line1 = reader.readLine();
					c++;
				}
				reader.close();
				fis.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			


			for(int i=0;i<arrayData.length;i++){
				for(int j=0;j<countLines;j++){
					System.out.print(charaMatrix[i][j]+ " "	);
				}
				System.out.println();
			}			

		/*try{
			BufferedReader br = new BufferedReader(new FileReader("adventure.txt"));
		
			String line=br.readLine();

			while(line!=null){
				StringTokenizer st= new StringTokenizer(line," ");
				while(st.hasMoreElements()){
					System.out.println(st.nextElement()+" " );
				}
				System.out.println();
				line=br.readLine();


			}

		}
		 catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		*/
	
	}
}


/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileDemo {
   public static void main(String[] args) {

       BufferedReader br = null;
       BufferedReader br2 = null;
       try{	
           br = new BufferedReader(new FileReader("B:\\myfile.txt"));		

           //One way of reading the file
	   System.out.println("Reading the file using readLine() method:");
	   String contentLine = br.readLine();
	   while (contentLine != null) {
	      System.out.println(contentLine);
	      contentLine = br.readLine();
	   }

	   br2 = new BufferedReader(new FileReader("B:\\myfile2.txt"));

	   //Second way of reading the file
	   System.out.println("Reading the file using read() method:");
	   int num=0;
	   char ch;
	   while((num=br2.read()) != -1)
	   {	
               ch=(char)num;
	       System.out.print(ch);
	   }

       } 
       catch (IOException ioe) 
       {
	   ioe.printStackTrace();
       } 
       finally 
       {
	   try {
	      if (br != null)
		 br.close();
	      if (br2 != null)
		 br2.close();
	   } 
	   catch (IOException ioe) 
           {
		System.out.println("Error in closing the BufferedReader");
	   }
	}
   }
}*/
