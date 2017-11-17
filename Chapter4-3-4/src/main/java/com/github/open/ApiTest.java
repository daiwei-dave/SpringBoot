package com.github.open;

import com.gome.commons.core.ErrorCode;
import com.gome.commons.core.ResultData;
import com.rop.AbstractRopRequest;
import com.rop.annotation.*;
import com.rop.session.Session;
import com.rop.session.SimpleSession;

/**
 * Created by daiwei on 2017/11/17.
 */
@ServiceMethodBean
public class ApiTest {

//    @ServiceMethod(method = "user.getSession", version = "1.0",ignoreSign = IgnoreSignType.NO, needInSession = NeedInSessionType.NO)
//    public Object getSession(LogonRequest request) {
//        //创建一个会话
//        SimpleSession session = new SimpleSession();
//        session.setAttribute("userName",request.getUserName());
//        request.getRopRequestContext().addSession("mockSessionId1", session);
//        //返回响应
//        LogonResponse logonResponse = new LogonResponse();
//        logonResponse.setSessionId("mockSessionId1");
//        return logonResponse;
//    }


    @ServiceMethod(method = "demo.sessionTest", version = "1.0",ignoreSign = IgnoreSignType.NO, needInSession = NeedInSessionType.NO)
    public ResultData<String> sessionTest(AbstractRopRequest request) {
        Session session = request.getRopRequestContext().getSession();
        if (session==null){
            return ResultData.newResultData(ErrorCode.FAILOR,"session为空");
        }
        String empNo = (String)session.getAttribute("empNo");
        return ResultData.newResultData(empNo);
    }


}
