package com.dvdrental.batch.business.partition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * date : 2020/11/26
 * file_name : FilmPartition
 * package_name : com.dvdrental.batch.business.partition
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Slf4j
public class FilmPartition implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>(gridSize);
        try {
            ExecutionContext context = new ExecutionContext();
            //zabbix 서버만 큼 Loop 돌려서 등록
//            result.put(new StringBuffer().append(PARTITION_NM).append("_").append("zabbix1").toString(), context);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
