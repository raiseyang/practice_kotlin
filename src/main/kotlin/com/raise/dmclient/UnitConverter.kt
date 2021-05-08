package com.raise.dmclient

import java.io.ByteArrayOutputStream

//   <?xml version="1.0" encoding="UTF-8"?>
//   <SyncML xmlns='SYNCML:SYNCML1.1'>
//       <SyncHdr>
//           <VerDTD>1.1</VerDTD>
//           <VerProto>DM/1.1</VerProto>
//           <SessionID>b</SessionID>
//           <MsgID>1</MsgID>
//           <Target>
//               <LocURI>http://127.0.0.1:8080/funambol/dm</LocURI>
//           </Target>
//           <Source>
//               <LocURI>IMEI:861139020041601</LocURI>
//               <LocName>funambol</LocName>
//           </Source>
//           <Cred>
//               <Meta>
//                   <Format xmlns='syncml:metinf'>b64</Format>
//                   <Type xmlns='syncml:metinf'>syncml:auth-md5</Type>
//               </Meta>
//               <Data>xOElbIggkNEAGmR82P9R5g==</Data>
//           </Cred>
//           <Meta>
//               <MaxMsgSize xmlns='syncml:metinf'>20000</MaxMsgSize>
//           </Meta>
//       </SyncHdr>
//       <SyncBody>
//           <Alert>
//               <CmdID>1</CmdID>
//               <Data>1201</Data>
//           </Alert>
//           <Replace>
//               <CmdID>2</CmdID>
//               <Item>
//                   <Source>
//                       <LocURI>./DevInfo/Lang</LocURI>
//                   </Source>
//                   <Data>en-us</Data>
//               </Item>
//               <Item>
//                   <Source>
//                       <LocURI>./DevInfo/DmV</LocURI>
//                   </Source>
//                   <Data>4.0</Data>
//               </Item>
//               <Item>
//                   <Source>
//                       <LocURI>./DevInfo/Mod</LocURI>
//                   </Source>
//                   <Data>scts devman</Data>
//               </Item>
//               <Item>
//                   <Source>
//                       <LocURI>./DevInfo/Man</LocURI>
//                   </Source>
//                   <Data>SyncML</Data>
//               </Item>
//               <Item>
//                   <Source>
//                       <LocURI>./DevInfo/DevId</LocURI>
//                   </Source>
//                   <Data>IMEI:861139020041601</Data>
//               </Item>
//           </Replace>
//           <Final/>
//       </SyncBody>
//   </SyncML>
//02 00 00 6A 1D 2D 2F 2F 53 59 4E 43 4D 4C 2F 2F
//44 54 44 20 53 79 6E 63 4D 4C 20 31 2E 31 2F 2F
//45 4E 6D 6C 71 03 31 2E 31 00 01 72 03 44 4D 2F
//31 2E 31 00 01 65 03 62 00 01 5B 03 31 00 01 6E
//57 03 68 74 74 70 3A 2F 2F 31 32 37 2E 30 2E 30
//2E 31 3A 38 30 38 30 2F 66 75 6E 61 6D 62 6F 6C
//2F 64 6D 00 01 01 67 57 03 49 4D 45 49 3A 38 36
//31 31 33 39 30 32 30 30 34 31 36 30 31 00 01 56
//03 66 75 6E 61 6D 62 6F 6C 00 01 01 4E 5A 00 01
//47 03 62 36 34 00 01 53 03 73 79 6E 63 6D 6C 3A
//61 75 74 68 2D 6D 64 35 00 01 01 00 00 4F 03 78
//4F 45 6C 62 49 67 67 6B 4E 45 41 47 6D 52 38 32
//50 39 52 35 67 3D 3D 00 01 01 5A 00 01 4C 03 32
//30 30 30 30 00 01 01 01 00 00 6B 46 4B 03 31 00
//01 4F 03 31 32 30 31 00 01 01 60 4B 03 32 00 01
//54 67 57 03 2E 2F 44 65 76 49 6E 66 6F 2F 4C 61
//6E 67 00 01 01 4F 03 65 6E 2D 75 73 00 01 01 54
//67 57 03 2E 2F 44 65 76 49 6E 66 6F 2F 44 6D 56
//00 01 01 4F 03 34 2E 30 00 01 01 54 67 57 03 2E
//2F 44 65 76 49 6E 66 6F 2F 4D 6F 64 00 01 01 4F
//03 73 63 74 73 20 64 65 76 6D 61 6E 00 01 01 54
//67 57 03 2E 2F 44 65 76 49 6E 66 6F 2F 4D 61 6E
//00 01 01 4F 03 53 79 6E 63 4D 4C 00 01 01 54 67
//57 03 2E 2F 44 65 76 49 6E 66 6F 2F 44 65 76 49
//64 00 01 01 4F 03 49 4D 45 49 3A 38 36 31 31 33
//39 30 32 30 30 34 31 36 30 31 00 01 01 01 12 01
//01
fun hexStrToByteArray(hexStr: String) {
    val hexStr1 = hexStr.replace("\n", "")
    val hexArray = hexStr1.split(" ")
    val byteArrayOutputStream = ByteArrayOutputStream()
    // 写入每个byte入流
    hexArray.forEachIndexed { index: Int, it: String ->
        print("it[$index]=$it")
        if (it.length == 2) {
            val byte = Integer.parseInt(it, 16).toByte()
            print(",byte[$index]=$byte  ")
            byteArrayOutputStream.write(byteArrayOf(byte))
        }
    }
    // 写进文件中
//    byteArrayOutputStream.writeTo(FileOutputStream("F:\\temp\\text002.wbxml"))
    byteArrayOutputStream.close()
}

fun main() {
    val hexStrings = """
        02 00 00 6A 1D 2D 2F 2F 53 59 4E 43 4D 4C 2F 2F 
        44 54 44 20 53 79 6E 63 4D 4C 20 31 2E 31 2F 2F 
        45 4E 6D 6C 71 03 31 2E 31 00 01 72 03 44 4D 2F 
        31 2E 31 00 01 65 03 62 00 01 5B 03 31 00 01 6E 
        57 03 68 74 74 70 3A 2F 2F 31 32 37 2E 30 2E 30 
        2E 31 3A 38 30 38 30 2F 66 75 6E 61 6D 62 6F 6C 
        2F 64 6D 00 01 01 67 57 03 49 4D 45 49 3A 38 36 
        31 31 33 39 30 32 30 30 34 31 36 30 31 00 01 56 
        03 66 75 6E 61 6D 62 6F 6C 00 01 01 4E 5A 00 01 
        47 03 62 36 34 00 01 53 03 73 79 6E 63 6D 6C 3A 
        61 75 74 68 2D 6D 64 35 00 01 01 00 00 4F 03 78 
        4F 45 6C 62 49 67 67 6B 4E 45 41 47 6D 52 38 32 
        50 39 52 35 67 3D 3D 00 01 01 5A 00 01 4C 03 32 
        30 30 30 30 00 01 01 01 00 00 6B 46 4B 03 31 00 
        01 4F 03 31 32 30 31 00 01 01 60 4B 03 32 00 01 
        54 67 57 03 2E 2F 44 65 76 49 6E 66 6F 2F 4C 61 
        6E 67 00 01 01 4F 03 65 6E 2D 75 73 00 01 01 54 
        67 57 03 2E 2F 44 65 76 49 6E 66 6F 2F 44 6D 56 
        00 01 01 4F 03 34 2E 30 00 01 01 54 67 57 03 2E 
        2F 44 65 76 49 6E 66 6F 2F 4D 6F 64 00 01 01 4F 
        03 73 63 74 73 20 64 65 76 6D 61 6E 00 01 01 54 
        67 57 03 2E 2F 44 65 76 49 6E 66 6F 2F 4D 61 6E 
        00 01 01 4F 03 53 79 6E 63 4D 4C 00 01 01 54 67 
        57 03 2E 2F 44 65 76 49 6E 66 6F 2F 44 65 76 49 
        64 00 01 01 4F 03 49 4D 45 49 3A 38 36 31 31 33 
        39 30 32 30 30 34 31 36 30 31 00 01 01 01 12 01 
        01 
    """.trimIndent()
    hexStrToByteArray(hexStrings)
}