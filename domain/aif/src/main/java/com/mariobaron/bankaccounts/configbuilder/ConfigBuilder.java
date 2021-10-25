package com.mariobaron.bankaccounts.configbuilder;


//import grupoexito.portalmandato.common.CustomEventsGateway;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ConfigBuilder {

  //  private final CustomEventsGateway customEventsGateway;
    private final ConfigParameters configParameters;
}
