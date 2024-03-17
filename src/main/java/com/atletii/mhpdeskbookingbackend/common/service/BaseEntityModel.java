package com.atletii.mhpdeskbookingbackend.common.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class BaseEntityModel {
    protected UUID id;
    protected long version;
    protected ZonedDateTime created;
    protected ZonedDateTime updated;
}
