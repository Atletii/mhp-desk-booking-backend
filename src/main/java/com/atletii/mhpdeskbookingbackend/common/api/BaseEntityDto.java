package com.atletii.mhpdeskbookingbackend.common.api;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseEntityDto {
    protected UUID id;
    protected long version;
    protected ZonedDateTime created;
    protected ZonedDateTime updated;
}
