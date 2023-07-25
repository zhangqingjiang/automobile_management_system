package com.zhang.automobile_management_system.service.systemMessage;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.Announcement;
import org.springframework.stereotype.Repository;

/**
 * @author ZQJ
 * @version 1.0
 * @description:对系统公告的各种操作
 * @throws
 * @date 2023/4/16 21:13
 */
@Repository
public interface SystemMessage {

    PageInfo<Announcement> selectAllAnnouncement();

    int insertAnnouncement(Announcement announcement);

    int deleteAllAnnouncement(Announcement announcement);
}
