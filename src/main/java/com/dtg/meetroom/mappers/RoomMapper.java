package com.dtg.meetroom.mappers;

import com.dtg.meetroom.model.Room;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room toModel(Room room);
}
