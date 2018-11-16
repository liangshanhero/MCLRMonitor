package cn.edu.scau.cmi.colorCheck.machineLearning;
//方程
public class Sequation
{     
    public Sequation()     
    {     
    }     
    /***********************************************************************
    *��Ҫ˵����ȫѡ��Ԫ��˹��Ԫ��   
    *���ܣ������Է�����   
    *���������   
    *    @param  double[][]  coeffA  ϵ������ n��n   
    *    @param  double[][]  constB  ������  ���Է�������Ҷ� n��m   
    *    @param  double[][]  resultX �������Է�����Ľ�   n��m   
    *    @param  int    n  ����coeffA�Ľ���   
    *    @param  int    m  ����const������   
    *���������   
    *    @return double abs  ����coeffA������ʽ,���abs=0���Ƚϸ��ӣ���������������,��Ϊû��ϣ���õ��Ľ⵱|coeffA|=0,������������   
    **********************************************************************/     
    public static  double  guassEquation(double[][] coeffA ,double[][] constB, double[][] resultX , int n , int m )     
    {     
        int i,j,k,row,line;     
        double temp,max,abs=1;     
        /*   
        *change���ڼ���ϵ�������н�������Ϣ   
        */     
        int[] change = new int[n] ;     
        for(i=0;i<n;i++) change[i]=i ;     
        /*   
        *�Ӿ���ĵ�һ�п�ʼ   
        *a������Ԫ   
        *b�����л���   
        *c�����Ա任   
        */     
        for(i=0;i<n-1;i++)     
        {     
            /*   
            *����Ԫ   
            */     
            row=i;line=i; max = Math.abs(coeffA[i][i]);     
            for(j=i;j<n;j++)     
            {     
                for(k=i;k<n;k++)     
                    {     
                        temp = Math.abs(coeffA[j][k]);     
                        if(temp>max)     
                            {     
                            max = temp;     
                            row = j;     
                            line = k;     
                             
                            }     
                 
                    }     
            }     
            /*   
            *��Ԫ�ҵ���Ϊ��row�У���line�У�ֵΪmax   
            *���max��0 ����ʾ����ʽΪ0������0���˳�   
            */     
            if(max==0)     
            {     
                return 0;     
            }     
            /*   
            *�ڶ��������л�����׼�����б任   
            */     
            if(row != i)     
            {     
                for(k=i;k<n;k++)     
                {     
                    temp = coeffA[i][k];     
                    coeffA[i][k] = coeffA[row][k];     
                    coeffA[row][k] = temp ;     
                }     
                for(k=0;k<m;k++)     
                {     
                    temp=constB[i][k];     
                    constB[i][k]=constB[row][k];     
                    constB[row][k]=temp;     
                }     
            }     
     
            if(line != i)     
            {     
                for(j=0;j<n;j++)     
                {     
                    temp = coeffA[j][line];     
                    coeffA[j][line]= coeffA[j][i];     
                    coeffA[j][i]=temp;     
                }     
                /*   
                *���ر���λ�õı仯���б任��Ϣ��ʶ�˱���λ�õı仯��Ϣ��   
                */     
                k=change[i];     
                change[i]=change[line];     
                change[line]= k;     
            }     
     
            /*   
            *��ʼ���Ա任,�ȶԵ�i�й�һ����Ȼ����������Ա任   
            */     
            abs *=coeffA[i][i];     
            for(k=i+1;k<n;k++) coeffA[i][k]/=coeffA[i][i];     
            for(k=0;k<m;k++) constB[i][k] /= coeffA[i][i];     
            coeffA[i][i]=1;     
     
            /*   
            *�����任   
            */     
            for(j=i+1;j<n;j++)     
            {     
                for(k=i+1;k<n;k++) coeffA[j][k] -= coeffA[j][i]*coeffA[i][k];     
                for(k=0;k<m;k++) constB[j][k] -= coeffA[j][i]*constB[i][k];     
                coeffA[j][i] =0 ;     
                 
            }     
     
        }     
        abs *= coeffA[n-1][n-1];     
     
        /*   
        *�ش���Ԫ   
        */     
        for(k=0;k<m;k++)     
        {     
            constB[n-1][k] /= coeffA[n-1][n-1];     
            for(i=n-2;i>=0;i--)     
                for(j=i+1;j<n;j++)     
                constB[i][k]-=coeffA[i][j]*constB[j][k];     
        }     
     
        /*   
        *����change����������˳�򣬵�����   
        */     
        for(i=0;i<n;i++)     
        {     
            for(j=0;j<m;j++)     
            {     
            resultX[change[i]][j]=constB[i][j];     
            }     
        }     
        return abs ;     
    }     
}    