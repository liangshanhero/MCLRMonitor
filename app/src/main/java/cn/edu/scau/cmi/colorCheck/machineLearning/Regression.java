package cn.edu.scau.cmi.colorCheck.machineLearning;
//第一种多元线性回归方法的使用。

import cn.edu.scau.cmi.colorCheck.util.MachineLearningUtils;

public class Regression {
    public Regression() {

    }

    public static double LineRegression(
            double[][] X,
            double[] Y,
            double[] K,
            int n,
            int m
    ) {
        double result = 0;

        int XLen = n + 1;
        int YLen = 1;
        int i, j, k;
        double[][] coeffX = new double[XLen][XLen];
        double[][] constY = new double[XLen][1];
        double[][] resultK = new double[XLen][1];

        double[][] temp = new double[m + 1][n + 1];
        for (i = 0; i < n + 1; i++) {
            temp[0][i] = 1;
        }
        for (i = 0; i < m + 1; i++) {
            temp[i][0] = 1;
        }
        for (i = 1; i < m + 1; i++)
            for (j = 1; j < n + 1; j++)
                temp[i][j] = X[i - 1][j - 1];

        for (i = 0; i < n + 1; i++) {
            for (j = i; j < n + 1; j++) {
                double col = 0;
                for (k = 1; k < m + 1; k++)
                    col += (temp[k][i] * temp[k][j]);
                coeffX[i][j] = col;
                coeffX[j][i] = col;
            }
            double conTemp = 0;
            for (k = 1; k < m + 1; k++)
                conTemp += (Y[k - 1] * temp[k][i]);
            constY[i][0] = conTemp;

        }
        result = Sequation.guassEquation(coeffX, constY, resultK, XLen, 1);
        if (result == 0) {
            //System.out.println("The regression is failed,please check the sample point \n");     
            return result;
        } else {
            for (i = 0; i < n + 1; i++)
                K[i] = resultK[i][0];
        }
        return result;
    }

    public static double optLineRegression(double[][] X,
                                           double[] Y,
                                           double[] K,
                                           int n,
                                           int m,
                                           double retainRate,
                                           int[] LossPoint) {

        double res = -1;
        if (n < 1 || m < 1) {
            //System.out.println("The parameter is not normal ,please check it\n");     
            return res;
        }
        if (retainRate >= 1.0 || retainRate <= 0.0) {
            //System.out.println("The retain parameter is not in 0 and 1 \n");     
            return res;
        }

        Double Dtemp = new Double(m * retainRate);
        int minsample = Dtemp.intValue();

        int lossnum = 0;

        int[] LossPointTemp = new int[m];

        double temp = LineRegression(X, Y, K, n, m);
        if (temp == 0) {
            //System.out.println("The regression operation is failed\n");     
            return res;
        }
        double ErrorStd = avgerror(X, Y, K, n, m);

        double[][] SampleX = X;
        double[] SampleY = Y;
        double[] CoeffK = K;
        int SampleNum = m;

        /*
         *��������λ�õı仯���
         */
        int[] change = new int[m];
        for (int k = 0; k < m; k++)
            change[k] = k;
        int index_max = -1;

        for (int i = m; i > minsample; i--) {
            index_max = maxErrorIndex(SampleX, SampleY, CoeffK, n, SampleNum);

            if (index_max == -1)
                return -1;


            int Loss = change[index_max];
            lossnum += 1;
            SampleNum -= 1;


            double[][] SampleXTemp = SampleX;
            SampleX = new double[SampleNum][n];

            double[] SampleYTemp = SampleY;
            SampleY = new double[SampleNum];

            for (int j = 0; j < index_max; j++) {
                for (int k = 0; k < n; k++) {
                    SampleX[j][k] = SampleXTemp[j + 1][k];
                }
                SampleY[j] = SampleYTemp[j];
            }

            for (int j = index_max; j < SampleNum; j++) {
                for (int k = 0; k < n; k++) {
                    SampleX[j][k] = SampleXTemp[j + 1][k];
                }
                SampleY[j] = SampleYTemp[j + 1];
                change[j] = change[j + 1];

            }
            res = LineRegression(SampleX, SampleY, CoeffK, n, SampleNum);


            double ErrorOpt = avgerror(SampleX, SampleY, CoeffK, n, SampleNum);

            if (ErrorOpt >= ErrorStd)//�Ż�����û���κθ���
            {
                return 1;
            } else {

                LossPoint[m - i] = Loss;

                K = CoeffK;

            }
        }
        return 1;
    }

    public static double avgerror(double[][] X, double[] Y, double[] K, int n, int m) {
        double res = -1;
        /*
         *YF���ڴ��ģ�͵�Ԥ����
         */
        double[] YF = new double[m];
        for (int i = 0; i < m; i++) {
            YF[i] = K[0];
            for (int j = 0; j < n; j++)
                YF[i] += X[i][j] * K[j + 1];
        }
        res = MachineLearningUtils.dimaddavg(Y, YF);
        return res;
    }

    public static int maxErrorIndex(double[][] X, double[] Y, double[] K, int n, int m) {
        int index_id = -1;
        /*
         *YF���ڴ��ģ�͵�Ԥ����
         */
        double[] YF = new double[m];
        for (int i = 0; i < m; i++) {
            YF[i] = K[0];
            for (int j = 0; j < n; j++)
                YF[i] += X[i][j] * K[j + 1];
        }

        index_id = MachineLearningUtils.maxerrordim(Y, YF);
        return index_id;
    }
}