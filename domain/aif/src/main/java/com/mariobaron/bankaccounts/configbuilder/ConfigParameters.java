package com.mariobaron.bankaccounts.configbuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ConfigParameters {

    private final String componentName;
    private final String serviceName;
    private final String operation;

}
