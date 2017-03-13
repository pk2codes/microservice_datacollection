package contracts;
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url value(consumer(regex('/datalocation/load/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}')),
            producer('/datalocation/load/d957028b-8dd4-48c7-b786-f74248433404'))
    }
    response {
        status 200
        body([
                id: 'd957028b-8dd4-48c7-b786-f74248433404',
                url: 'http://testurl.test'
        ])
        headers {
            header('Content-Type': value (
                    producer(regex('application/json.*')),
                    consumer('application/json')
            ))
        }
    }
}