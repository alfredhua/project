package com.website.dto;

import com.website.dto.entity.Setting;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Getter
@Setter
public class SettingRespDTO extends Setting {

    long key;

    List<SettingRespDTO> children;

    public long getKey() {
        return this.getId();
    }
}
