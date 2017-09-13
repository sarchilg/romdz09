package SevenZip;

import java.io.File;
import java.io.FileOutputStream;
import romdz09.functions;
import romdz09.myLog;

public class LzmaAlone
{
    public static int i8=8;
    public static int size=0;
    static int propertiesSize = 5;
    static public String head5c = "";
    static public boolean saveaab=true;
    
    static public void exec(String ss){
        try{
            LzmaAlone.main(functions.mysplit(ss));
        }catch(Exception e){
            myLog.Systemoutprintln("err=="+e.toString());
            e.printStackTrace();
        }
    }
	static public class CommandLine
	{
		public static final int kEncode = 0;
		public static final int kDecode = 1;
		public static final int kBenchmak = 2;
		
		public int Command = -1;
		public int NumBenchmarkPasses = 10;
		
		public int DictionarySize = 1 << 23;
		public boolean DictionarySizeIsDefined = false;
		
		public int Lc = 3;
		public int Lp = 0;
		public int Pb = 2;
		
		public int Fb = 128;
		public boolean FbIsDefined = false;
		
		public boolean Eos = false;
		
		public int Algorithm = 2;
		public int MatchFinder = 1;
		
		public String InFile;
		public String OutFile;
		
		boolean ParseSwitch(String s)
		{
			if (s.startsWith("d"))
			{
				DictionarySize = 1 << Integer.parseInt(s.substring(1));
				DictionarySizeIsDefined = true;
			}
			else if (s.startsWith("fb"))
			{
				Fb = Integer.parseInt(s.substring(2));
				FbIsDefined = true;
			}
			else if (s.startsWith("a"))
				Algorithm = Integer.parseInt(s.substring(1));
			else if (s.startsWith("lc"))
				Lc = Integer.parseInt(s.substring(2));
			else if (s.startsWith("lp"))
				Lp = Integer.parseInt(s.substring(2));
			else if (s.startsWith("pb"))
				Pb = Integer.parseInt(s.substring(2));
			else if (s.startsWith("i8"))
				i8 = Integer.parseInt(s.substring(2));                        
			else if (s.startsWith("eos"))
				Eos = true;
			else if (s.startsWith("mf"))
			{
				String mfs = s.substring(2);
				if (mfs.equals("bt2"))
					MatchFinder = 0;
				else if (mfs.equals("bt4"))
					MatchFinder = 1;
				else if (mfs.equals("bt4b"))
					MatchFinder = 2;
				else
					return false;
			}
			else
				return false;
			return true;
		}
		
		public boolean Parse(String[] args) throws Exception
		{
			int pos = 0;
			boolean switchMode = true;
			for (int i = 0; i < args.length; i++)
			{
				String s = args[i];
				if (s.length() == 0)
					return false;
                                System.out.println("s="+s);
				if (switchMode)
				{
					if (s.compareTo("--") == 0)
					{
						switchMode = false;
						continue;
					}
					if (s.charAt(0) == '-')
					{
						String sw = s.substring(1).toLowerCase();
						if (sw.length() == 0)
							return false;
						try
						{
							if (!ParseSwitch(sw))
								return false;
						}
						catch (NumberFormatException e)
						{
							return false;
						}
						continue;
					}
				}
				if (pos == 0)
				{
					if (s.equalsIgnoreCase("e"))
						Command = kEncode;
					else if (s.equalsIgnoreCase("d"))
						Command = kDecode;
					else if (s.equalsIgnoreCase("b"))
						Command = kBenchmak;
					else
						return false;
				}
				else if(pos == 1)
				{
					if (Command == kBenchmak)
					{
						try
						{
							NumBenchmarkPasses = Integer.parseInt(s);
							if (NumBenchmarkPasses < 1)
								return false;
						}
						catch (NumberFormatException e)
						{
							return false;
						}
					}
					else
						InFile = s;
				}
				else if(pos == 2)
					OutFile = s;
				else
					return false;
				pos++;
				continue;
			}
			return true;
		}
	}
	
	
	static void PrintHelp()
	{
		myLog.Systemoutprintln(
				"\nUsage:  LZMA <e|d> [<switches>...] inputFile outputFile\n" +
				"  e: encode file\n" +
				"  d: decode file\n" +
				"  b: Benchmark\n" +
				"<Switches>\n" +
				// "  -a{N}:  set compression mode - [0, 1], default: 1 (max)\n" +
				"  -d{N}:  set dictionary - [0,28], default: 23 (8MB)\n" +
				"  -fb{N}: set number of fast bytes - [5, 273], default: 128\n" +
				"  -lc{N}: set number of literal context bits - [0, 8], default: 3\n" +
				"  -lp{N}: set number of literal pos bits - [0, 4], default: 0\n" +
				"  -pb{N}: set number of pos bits - [0, 4], default: 2\n" +
				"  -mf{MF_ID}: set Match Finder: [bt2, bt4], default: bt4\n" +
				"  -eos:   write End Of Stream marker\n"
				);
	}
	
	public static void main(String[] args) throws Exception
	{       nn=-1;
		//myLog.Systemoutprintln("\nLZMA (Java) 4.61  2008-11-23\n"+args.length);
		
		if (args.length < 1||(args.length==1)&&args[0].equals(""))
		{
			PrintHelp();
			return;
		}
		
		CommandLine params = new CommandLine();
		if (!params.Parse(args))
		{
			myLog.Systemoutprintln("\nIncorrect command");
                        PrintHelp();
			return;
		}
		
		if (params.Command == CommandLine.kBenchmak)
		{
			int dictionary = (1 << 21);
			if (params.DictionarySizeIsDefined)
				dictionary = params.DictionarySize;
			if (params.MatchFinder > 1)
				throw new Exception("Unsupported match finder");
			SevenZip.LzmaBench.LzmaBenchmark(params.NumBenchmarkPasses, dictionary);
		}
		else if (params.Command == CommandLine.kEncode || params.Command == CommandLine.kDecode)
		{
			java.io.File inFile = new java.io.File(params.InFile);
			java.io.File outFile = new java.io.File(params.OutFile);
			
			java.io.BufferedInputStream inStream  = new java.io.BufferedInputStream(new java.io.FileInputStream(inFile));
			java.io.BufferedOutputStream outStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(outFile));
			
			boolean eos = false;
                        int ibb=0;
			if (params.Eos)
				eos = true;
			if (params.Command == CommandLine.kEncode)
			{
				SevenZip.Compression.LZMA.Encoder encoder = new SevenZip.Compression.LZMA.Encoder();
				if (!encoder.SetAlgorithm(params.Algorithm))
					throw new Exception("Incorrect compression mode");
				if (!encoder.SetDictionarySize(params.DictionarySize))
					throw new Exception("Incorrect dictionary size");
				if (!encoder.SetNumFastBytes(params.Fb))
					throw new Exception("Incorrect -fb value");
				if (!encoder.SetMatchFinder(params.MatchFinder))
					throw new Exception("Incorrect -mf value");
				if (!encoder.SetLcLpPb(params.Lc, params.Lp, params.Pb))
					throw new Exception("Incorrect -lc or -lp or -pb value");
				encoder.SetEndMarkerMode(eos);
				encoder.WriteCoderProperties(outStream);
				long fileSize;
				if (eos)
					fileSize = -1;
				else
					fileSize = inFile.length();
				for (int i = 0; i < i8; i++)
					outStream.write((int)(fileSize >>> (8 * i)) & 0xFF);
				encoder.Code(inStream, outStream, -1, -1, null);
			}
			else
			{
				
                                boolean da=true;
				byte[] properties = new byte[propertiesSize];
                                String[] h5c=head5c.split(" ");
                                if (h5c.length>=propertiesSize){
                                    for (int i=0;i<propertiesSize;i++){
                                        properties[i]=functions.str2byte(h5c[i]);
                                    }
                                }else
                                
				if (inStream.read(properties, 0, propertiesSize) != propertiesSize){
                                    myLog.error("input .lzma file is too short");
                                    da=false;
                                }
					//throw new Exception("input .lzma file is too short");
//properties[0]=0x5b;

                                
				SevenZip.Compression.LZMA.Decoder decoder=null;
                                if (da) decoder= new SevenZip.Compression.LZMA.Decoder();
                                if (da) 
				if (!decoder.SetDecoderProperties(properties)){
                                    myLog.error("Incorrect stream properties");
                                    da=false;                                    
                                }
					//throw new Exception("Incorrect stream properties");
				long outSize = 0;
                                
                                if (da) 
				for (int i = 0; i < i8; i++)//8
				{
					int v = inStream.read();
					if (v < 0){
                                            myLog.error("Can't read stream size");
                                            da=false;
                                            break;
                                        }
					//	throw new Exception("Can't read stream size");
                                        if (size<=0)
                                            outSize |= ((long)v) << (8 * i);
                                        //System.out.println(""+(long)v);
				}
                                System.out.println("outSize="+outSize);
                                if (size>0)
                                    outSize=size;
                                if (outSize>10000000) outSize=10000000;
                                System.out.println("outSize="+outSize);
                                if (da) {
				if (!decoder.Code(inStream, outStream, outSize)){
				//	throw new Exception("Error in data stream");
                                    myLog.Systemoutprintln("Error in data stream");
                                }else myLog.Systemoutprintln("uuuuuuuuuuuurrrrrrrrrrrrrrraaaaaaa!!!!!!!!========");
                                }
                                ibb=decoder.m_RangeDecoder.bb;
			}
			outStream.flush();
			outStream.close();
                        if (saveaab&&params.Command != CommandLine.kEncode){
                            nn=0;byte[] bb=new byte[1000];

                            File fil=new File(params.InFile);
                            String path=fil.getParent();
                            String ss=fil.getName();
                            //System.out.println(""+ss+"=="+path);
                            int p1=ss.lastIndexOf(".");
                            int ii=functions.str2int("#"+ss.substring(0,p1))+ibb+i8+propertiesSize;
                            //System.out.println(""+ss.substring(0,p1));
                            String ff=path+System.getProperty("file.separator")+functions.tohex(ii).trim()+"a.b";
                            //System.out.println("file=="+ff+"=="+ii+"=="+ibb);
                            FileOutputStream fs = null;
                            while (true){

                                int k=inStream.read(bb);
                                if (k>=0) nn+=k;
                                if (k<50) break;
                                if (fs==null) fs=functions.byte2file1(ff);
                                functions.byte2file2(ff, bb,0,k,fs);
                                if (k<bb.length) break;
                            }
                            functions.byte2file3(fs);

                            inStream.close();
                        }
		}//e -lc3 -d16 -fb32 -mfbt2 /home/user/Desktop/1/_00/dop/6/1/tmp1111 /home/user/Desktop/1/_00/dop/6/1/tmp11112
		else
			throw new Exception("Incorrect command");
		return;
	}
        public static long nn=-1;
}
