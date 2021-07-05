package com.common.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/05/25
 */
@Getter
@Setter
public class BaseDomainDTO {

    private List<String> auth_code_list;

}
