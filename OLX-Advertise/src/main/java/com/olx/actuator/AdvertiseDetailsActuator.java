/* 
 * package com.olx.actuator;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * import javax.annotation.PostConstruct;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.actuate.endpoint.annotation.Endpoint; import
 * org.springframework.boot.actuate.endpoint.annotation.ReadOperation; import
 * org.springframework.stereotype.Component;
 * 
 * import com.olx.repository.AdvertiseRepository;
 * 
 * @Component
 * 
 * @Endpoint(id="order") public class AdvertiseDetailsActuator {
 * 
 * private Map<String, Integer> map = new HashMap<String, Integer>();
 * 
 * @Autowired AdvertiseRepository advertiseRepository;
 * 
 * @PostConstruct public void init() { map.put("totalOrder",
 * advertiseRepository.findAll().size()); }
 * 
 * @ReadOperation public Map<String, Integer> getAllData(){ return map; } }
 */