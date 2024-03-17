package com.atletii.mhpdeskbookingbackend.rooms.persistance.repository;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseRepository;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends BaseRepository<RoomEntity> {
}
