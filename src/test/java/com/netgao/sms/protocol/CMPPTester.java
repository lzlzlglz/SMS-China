package com.netgao.sms.protocol;

import com.netgao.sms.protocol.cmpp.CMPPConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: gaudi.gao
 * Date: 14-6-17
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
public class CMPPTester {
    private static Logger log = LogManager.getLogger(CMPPTester.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) {
        CMPPConnection conn = new CMPPConnection();
        conn.setSourceAddr("123456");
        conn.setPassword("aaa001");
        conn.setVersion((byte) 0);
        conn.setAutoReconnect(true);
        conn.setSendInterval(200);

        conn.connect("127.0.0.1", 7890);

        if(conn.isConnected()){

            Session session = conn.getSession();

            String[] phones = new String[] { "13162645136" };

            long startTime = System.currentTimeMillis();
            try {
                for(int i = 0; i < phones.length * 10; i++) {
                    String content = String.format("第%d条:电信cmpp测试X(%s)", i+1, format.format(new Date()));
                    session.submit(content, "1065902100612", phones[i/10]);

                }
            }
            finally {
                log.info(String.format("total:%d",System.currentTimeMillis()-startTime));
            }
        }
    }
}
