package tp.services.flights.fsm;

import com.despegar.automation.commons.config.model.Configuration;

import tp.services.flights.DespegarService;

public abstract class FSMService
    extends DespegarService {

    public FSMService(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected String builderEndpoint() {
        return this.configuration.getEnvironment().getKeys().get("endpoint_FSM");
    }

}
