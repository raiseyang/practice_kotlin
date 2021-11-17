package test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    //Step 1 SHA-256 Hash of the IMEI 15-digit decimal IMEI.
    //Step 2 HMAC-SHA256 of the IMEI_HASH using the MASTER_KEY.
    //64-character lowercase value result of:
    //HMAC-SHA256 (MASTER_KEY, IMEI_HASH)
    //Step 3 Concatenate the last 8 digits of the IMEI and the pre-shared
    //model constant as a string.
    //Result of concatenation is known as CONCAT
    //Step 4 HMAC-SHA256 of the sting result of Step 3.
    //64-character lowercase string using the result of Step 2 as
    //the PRK_KEY.
    //HMAC-SHA256(PRK_KEY, CONCAT)




    // in document
//    String imei = "352574111617773";
//    String OEM_Master_Key ="662e7534a4bf5b18ca882dcb423e94d2ec91d83716f389c47f4a8a14812caed2";
//    String token = "148350abcdef";


    //imei=862533050022406,Master_Key=2JPyiMBRi_bWdI%wKpQyOMVGo@*OLF3cFc_0^q!N7ACiIc!HjwQM6=*0R+!fTEA4,token=eWV7AUebOYh6
    public static void main(String[] args) {
        // in my phone
        String imei = "862533056565656";
        String OEM_Master_Key ="2JPyiMBRi_bWdI%wKpQyOMVGo@*OLF3cFc_0^q!N7ACiIc!HjwQM6=*0R+!fTEA4";
        String token = "eWV7AUebOYh6";
        //Step 1
        String result1 = getSHA256StrJava(imei);
        System.out.println("result1="+result1);
//        System.out.println("expect1=d31d5f35bc3bd1d7b1ae474df8f7082619ea912658e1a566594de4342cecaa92");
        //Step 2
        String result2 = sha256_mac(OEM_Master_Key,result1);
        System.out.println("result2="+result2);
//        System.out.println("expect2=baa89dc29ac45bea55f4c0d6293440684a3a4f4e48befe9436b2086581075a71");
        //Step 3
        String d1 = imei.substring(7);
        String ConcatD1D2 = d1+token;
//        System.out.println("d1d2="+ConcatD1D2);
        String result3 = sha256_mac(ConcatD1D2,result2);
        System.out.println("result3="+result3);
//        System.out.println("expect3=b7702b5be71d8d4003ebb8a9c147eb1c764d4b780c7c88b3fe83f38207c3b56c");
    }
    public static String sha256_mac(String message,String key){
        String outPut= null;
        try{
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            outPut = byte2Hex(bytes);
        }catch (Exception e){
            System.out.println("Error HmacSHA256========"+e.getMessage());
        }
        return outPut;
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
