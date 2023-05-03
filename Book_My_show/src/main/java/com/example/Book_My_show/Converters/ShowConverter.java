package com.example.Book_My_show.Converters;

import com.example.Book_My_show.DTO.EntryDTO.ShowEntryDTO;
import com.example.Book_My_show.Entity.ShowEntity;

public class ShowConverter {
    public static ShowEntity convertToShowEntity(ShowEntryDTO showEntryDTO){
        return ShowEntity.builder().showType(showEntryDTO.getShowType())
                .showDate(showEntryDTO.getShowDate())
                .showTime(showEntryDTO.getShowTime())
                .build();
    }
}
