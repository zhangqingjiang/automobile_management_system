package com.zhang.automobile_management_system.service.systemMessage.systemMessageImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.Announcement;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.systemMessage.SystemMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 对系统公告的具体操作
 * @throws
 * @date 2023/4/16 21:16
 */
@Service
public class SystemMessageImpl extends BaseService implements SystemMessage {

    @Override
    public PageInfo<Announcement> selectAllAnnouncement() {
        PageHelper.startPage(1, 10);
        List<Announcement> announcements = announcementMapper.selectByExample(null);
        return new PageInfo<>(announcements,1);
    }

    @Override
    public int insertAnnouncement(Announcement announcement) {
        int insert = announcementMapper.insert(announcement);
        return insert;
    }

    @Override
    public int deleteAllAnnouncement(Announcement announcement) {
        announcementExample.createCriteria().andAnnouncementMessageEqualTo(announcement.getAnnouncementMessage());
        int delete = announcementMapper.deleteByExample(announcementExample);
        announcementExample.clear();
        return delete;
    }
}
