package cn.edu.scau.cmi.rgbstudy.machineLearning;
//第一种多元线性回归方法的使用。
import cn.edu.scau.cmi.rgbstudy.util.MachineLearningUtils;

public  class  Regression
{     
    public Regression()     
    {     
         
    }     
     
    /***************************************************************************************   
    *ģ�ͣ���Ԫ���Իع�   
    *��Ҫ˵��������һ�����������������Իع����ϵ�������Իع������ģ��Ϊ��Y=k0+k1X1+k2X2+....+knXn,���У�X1,X2,....,XnΪ�������YΪ����   
    *     k0,k1,....,knΪ�ع�ϵ�����ú�������Ҫ����һ��������Xi1,Xi2,...Xin,Yi��i=1,2,...,m[m������]��������С���˷���ԭ�򣬼����   
    *     ��ѵûع����ϵ��k0,k1,....,kn,�Ӷ��õ����Իع����ģ�ͣ���ģ���Լ���չ���Ϳ����ƹ㵽�����Իع�ģ��   
    *�������:   
    *    @param  double[][]  X  �Ա���������   
    *    @param  double[]    Y  ���������   
    *    @param  double[]   K  �ع�ϵ��   
    *    @param  int  n  �ع��������   
    *    @param  int  m  ��������   
    *���������   
    *    @return double result  0:ʧ�ܣ��������ɹ�   
    ****************************************************************************************/     
    public static double  LineRegression(     
                                            double[][] X,     
                                            double[] Y,     
                                            double[] K,     
                                            int n,     
                                            int m    
                                        )     
    {     
        double result = 0 ;     
             
        /*   
        *���Իع����⣬����ת��Ϊ��һ���Գ����Է�������������   
        *���Է������ϵ������Ϊn+1*n+1,��������Ϊn+1*1   
        */     
        int  XLen = n+1;     
        int  YLen = 1;     
        int i,j,k;     
        double[][]  coeffX = new double[XLen][XLen];     
        double[][]  constY = new double[XLen][1];     
        double[][]  resultK = new double[XLen][1];     
             
        /*   
        *���ݲ�����������Ҫ��ⷽ�����ϵ�����󡢳�������   
        */     
        double[][] temp = new double[m+1][n+1];     
        for(i =0;i<n+1;i++)     
        {     
            temp[0][i] = 1;     
        }     
        for(i =0;i<m+1;i++)     
        {     
            temp[i][0] = 1;     
        }     
        for( i=1;i<m+1;i++)     
            for( j=1;j<n+1;j++)     
            temp[i][j]= X[i-1][j-1];     
        /*   
        *��ʼ����ÿһ��ϵ��   
        */     
        for(i=0;i<n+1;i++)     
        {     
            /*   
            *coeffX�ĵ�i�к�i�е�ϵ����ע�⣬�ǶԳƾ���   
            */     
            for( j= i;j<n+1;j++){     
                double col = 0 ;     
                for(k=1;k<m+1;k++)     
                    col+= ( temp[k][i]*temp[k][j] );     
                coeffX[i][j] = col;     
                coeffX[j][i] = col;              
            }     
     
            /*   
            *constY�ĵ�i��Ԫ��   
            */     
            double conTemp =0 ;     
            for(k=1;k<m+1;k++)     
                    conTemp+= ( Y[k-1]*temp[k][i]);     
           constY[i][0]=conTemp;     
     
        }     
     
        /*   
        *����Sequation�����������Է�����   
        */     
        result = Sequation.guassEquation(coeffX,constY,resultK,XLen,1);      
        if(result ==0 )     
        {     
            //System.out.println("The regression is failed,please check the sample point \n");     
            return result;     
        }else{     
            for(i= 0;i<n+1;i++)     
                K[i] = resultK[i][0];     
        }     
        return result;     
    }     
     
    /*****************************************************************************************   
    *ģ�����ƣ��������Ż����Իع�   
    *��Ҫ˵������ģ���ǶԼ����Իع�ģ�͵ĸ���,������Ŀ��������ṩ�����������п�����Щ�쳣��ʹ��ģ�͵ľ��ȴ�󽵵ͣ�ϵͳͨ��һ�����������Զ�   
    *         �����ֶ�ģ�͵���϶����������㣬Ȼ��ȥ�����������ٽ��лع�����.��ģ����ʵ��ֵ��ģ��Ԥ��ֵ֮���ƽ��ƫ����Ϊ����ģ��׼ȷ�Ե�Ч   
    *         �ú�����   
    *         ���ƽ��ƫ����٣���ʶģ����ȥ���������ݺ�ģ����ϵ�Ч�����ӣ�����ֹͣ�����Ż��Ĳ��衣���⣬��������Ż������������ٱ�������������   
    *         ��ȷ����������������   
    *ע����ģ����δ�õ�������֤���������Լ��ľ����ܽ�   
    *��������:   
    *    @param  double[][]  X  �Ա���������   
    *    @param  double[]    Y  ���������   
    *    @param  int  n  �ع��������   
    *    @param  double[]   K  ���ջع�ϵ�������أ�   
    *    @param  int  m  ��������   
    *    @param   double retainRate  ������ͱ�����   
    *    @param  int[] LossPoint ��������������(����)   
    *���������   
    *    @return double res  -1:ʧ�ܣ�1���ɹ�   
    *******************************************************************************************/     
    public static  double optLineRegression( double[][] X,     
                                             double[] Y,     
                                             double[] K,     
                                             int n,     
                                             int m,     
                                             double retainRate ,     
                                             int[] LossPoint){     
             
        double res = -1;     
        if(n<1||m<1){     
            //System.out.println("The parameter is not normal ,please check it\n");     
            return res;          
        }     
        if(retainRate>=1.0||retainRate<=0.0){     
            //System.out.println("The retain parameter is not in 0 and 1 \n");     
            return res;     
        }     
             
        //����ȷ������С��������     
         
        Double Dtemp = new Double(m*retainRate);     
        int minsample = Dtemp.intValue();      
     
     
        //��������������ĸ���     
     
        int lossnum =0 ;     
     
        int[] LossPointTemp = new int[m];     
     
         
        //���е�һ�λع�     
     
        double temp = LineRegression(X,Y, K,n,m);      
        if(temp == 0){     
            //System.out.println("The regression operation is failed\n");     
            return res;          
        }     
     
        //��һ�ε�ƽ�����     
     
        double ErrorStd = avgerror(X, Y, K,n, m);     
     
        double[][] SampleX = X;     
        double[]   SampleY = Y;     
        double[]    CoeffK = K;     
        int SampleNum = m;     
     
        /*   
        *��������λ�õı仯���   
        */     
        int[] change = new int[m];     
        for(int k=0;k<m;k++)     
            change[k]=k;     
        int index_max = -1;     
     
        for (int i=m;i>minsample ;i-- )     
        {     
            /*   
            *�ҵ�ǰ�ع������е������������index��   
            */     
            index_max = maxErrorIndex(SampleX,SampleY,CoeffK,n,SampleNum);     
     
            if(index_max == -1)     
                return -1;     
     
            /*   
            *��index_maxΪ������������ȥ��������   
            */     
     
            int Loss = change[index_max];     
            lossnum +=1;     
            SampleNum -=1;     
     
     
            double[][] SampleXTemp = SampleX;     
            SampleX = new double[SampleNum][n];     
     
            double[] SampleYTemp = SampleY;     
            SampleY  = new double[SampleNum];     
     
            for(int j= 0;j<index_max;j++){     
                for(int k=0;k<n;k++)     
                {     
                SampleX[j][k]=SampleXTemp[j+1][k];     
                }     
                SampleY[j]=SampleYTemp[j];     
            }     
     
            for(int j= index_max;j<SampleNum;j++){     
                for(int k=0;k<n;k++){     
                    SampleX[j][k]=SampleXTemp[j+1][k];     
                }     
                SampleY[j] = SampleYTemp[j+1];     
                change[j] = change[j+1];     
     
            }     
     
            /*   
            *�����µ��������лع�   
            */     
            res=LineRegression(SampleX,SampleY,CoeffK,n,SampleNum);     
     
            /*   
            *�Ƚ��µ�Ԥ��ģ���������ģ�͵������û�и��������������򣬼�����ؽ����������   
            */     
     
            double  ErrorOpt = avgerror(SampleX,SampleY,CoeffK,n,SampleNum);     
     
            if (ErrorOpt>=ErrorStd)//�Ż�����û���κθ���     
            {     
                return  1;     
            }     
            else     
            {     
                 
            /*   
            *��������ɾ����������ػع�ϵ��   
            */     
            LossPoint[m-i] = Loss;     
     
            K=CoeffK;     
                             
            }     
        }     
        return 1;                
    }     
     
    /************************************************************************************   
    *��Ҫ˵��������ع�ģ�͵�ƽ�����   
    *�������:   
    *    @param  double[][]  X  �Ա���������   
    *    @param  double[]    Y  ���������   
    *    @param  int  n  �ع��������   
    *    @param  double[]   K  �ع�ϵ��   
    *    @param  int  m  ��������   
    *���������   
    *    @return double Ierror ��-1ʧ�� ƽ�����   
    *************************************************************************************/     
    public static double avgerror(double[][] X,double[] Y,double[] K,int n,int m){     
        double res = -1;     
        /*   
        *YF���ڴ��ģ�͵�Ԥ����   
        */     
        double[] YF = new double[m];      
        for(int i=0;i<m;i++){     
            YF[i] = K[0];      
            for(int j=0;j<n;j++)     
                YF[i]+=X[i][j]*K[j+1];               
        }     
             
        /*   
        *�����ʼԤ������ʵֵ����ƽ��ά�;ࣩ   
        */     
        res = MachineLearningUtils.dimaddavg(Y,YF);
        return res;     
    }     
     
     
    /************************************************************************************   
    *��Ҫ˵��������ع�ģ�͵���������������index_id   
    *�������:   
    *    @param  double[][]  X  �Ա���������   
    *    @param  double[]    Y  ���������   
    *    @param  int  n  �ع��������   
    *    @param  double[]   K  �ع�ϵ��   
    *    @param  int  m  ��������   
    *���������   
    *    @return int Index_id ��-1ʧ��    
    ************************************************************************************/     
    public static int maxErrorIndex(double[][] X,double[] Y,double[] K,int n,int m){     
        int index_id = -1;     
        /*   
        *YF���ڴ��ģ�͵�Ԥ����   
        */     
        double[] YF = new double[m];      
        for(int i=0;i<m;i++){     
            YF[i] = K[0];      
            for(int j=0;j<n;j++)     
                YF[i]+=X[i][j]*K[j+1];               
        }     
             
        /*   
        *������������������index   
        */     
        index_id = MachineLearningUtils.maxerrordim(Y,YF);
        return index_id;         
    }     
     
} 