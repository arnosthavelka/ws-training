package com.asseco.aha.training.ws.server.ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.asseco.aha.training.ws.calc.srv.v1.CalcPortType;
import com.asseco.aha.training.ws.server.service.CalculatorService;
import com.asseco.aha.training.ws.server.service.CalculatorServiceImpl;
import com.asseco.aha.training.ws.types.v1.AddRequest;
import com.asseco.aha.training.ws.types.v1.AddResponse;

public class JaxwsEndpoint {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/jaxws/CalcService", new JaxwsEndpoint().new JaxwsWebService());
    }

    @WebService(serviceName = "CalcService", portName = "CalcPort", endpointInterface = "com.asseco.aha.training.ws.calc.srv.v1.CalcPortType")
    public class JaxwsWebService implements CalcPortType {

        private CalculatorService service = new CalculatorServiceImpl();

        /*
         * (non-Javadoc)
         * 
         * @see com.asseco.aha.training.ws.v1.WsTrainingPortType#add(com.asseco.aha.training.types.v1.AddRequest)
         */
        @Override
        public AddResponse add(AddRequest request) {
            int value = service.add(request.getNumber());

            AddResponse response = new AddResponse();
            response.setResult(value);

            return response;
        }
    }

}
