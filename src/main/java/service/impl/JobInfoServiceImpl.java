package service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.JobInfo;
import mapper.JobInfoMapper;
import service.JobInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 560寝室
 * @since 2020-12-06
 */
@Service
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfo> implements JobInfoService {

    @Autowired
    JobInfoMapper jobInfoMapper;
    public List<JobInfo> listJobsWithCompanyName(Wrapper wrapper){
        return jobInfoMapper.listJobsWithCompanyName(wrapper);
    }
}
