package com.website.dao;
import com.pro.website.dto.entity.Notice;
import com.pro.website.dto.NoticeListReqDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2018/12/25
 */
public class NoticeProvider {


    public String updateNotice(Notice noticeReqDTO) {
        return new SQL() {
            {
                UPDATE("m_website.site_notice");
                if (noticeReqDTO.getType() != null) {
                    SET("type = #{type}");
                }
                if (noticeReqDTO.getTitle() != null) {
                    SET("title = #{title}");
                }
                if (noticeReqDTO.getContext() != null) {
                    SET("context = #{context}");
                }
                if (noticeReqDTO.getPublish() != null) {
                    SET("publish = #{publish}");
                }
                if (noticeReqDTO.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                if (noticeReqDTO.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                if (noticeReqDTO.getDel() != null) {
                    SET("del = #{isDelete}");
                }
                if (noticeReqDTO.getOrdering() != null) {
                    SET("ordering = #{ordering}");
                }
                if (noticeReqDTO.getClick_count() != null) {
                    SET("click_count = #{click_count}");
                }
                if (noticeReqDTO.getPublish_time() != null) {
                    SET("publish_time = #{publish_time}");
                }
                if(noticeReqDTO.getAuth_data_code()!=null){
                    SET("auth_data_code = #{auth_data_code}");

                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listNoticeByPage(NoticeListReqDTO noticeListReqDTO) {
        return new SQL() {
            {
                SELECT("id,type,title,context,publish,create_time,update_time,del,ordering,click_count,publish_time,auth_data_code");
                FROM("m_website.site_notice");
                WHERE("del= 0");
                if (noticeListReqDTO.getType() != null) {
                    WHERE("type = #{type}");
                }
                ORDER_BY(" create_time desc limit "+ noticeListReqDTO.getOffset()
                        +" ,"+ noticeListReqDTO.getPage_size());
            }
        }.toString();
    }

    public String listNoticeCount(NoticeListReqDTO noticeListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_notice");
                WHERE("del= 0");
                if (noticeListReqDTO.getType() != null) {
                    WHERE("type = #{type}");
                }
                ORDER_BY("create_time desc limit "+ noticeListReqDTO.getOffset()+" ,"
                        + noticeListReqDTO.getPage_size());
            }
        }.toString();
    }

}
