/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ibaochenyu.jzh_shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Canal Binlog 监听触发时间
 *
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanalBinlogEvent {

    /**
     * 变更数据
     */
    private List<Map<String, Object>> data;

    /**
     * 数据库名称
     */
    private String database;

    /**
     * es 是指 Mysql Binlog 里原始的时间戳，也就是数据原始变更的时间
     * Canal 的消费延迟 = ts - es
     */
    private Long es;

    /**
     * 递增 ID，从 1 开始
     */
    private Long id;

    /**
     * 当前变更是否是 DDL 语句   //DDL可以理解为对表的操作。数据库模式定义语言DDL(Data Definition Language)，是用于描述数据库中要存储的现实世界实体的语言。主要由create（添加）、alter（修改）、drop（删除）和 truncate（删除） 四个关键字完成。
     */
    private Boolean isDdl;

    /**
     * 表结构字段类型
     */
    private Map<String, Object> mysqlType;

    /**
     * UPDATE 模式下旧数据
     */
    private List<Map<String, Object>> old;

    /**
     * 主键名称
     */
    private List<String> pkNames;

    /**
     * SQL 语句
     */
    private String sql;

    /**
     * SQL 类型
     */
    private Map<String, Object> sqlType;

    /**
     * 表名
     */
    private String table;

    /**
     * ts 是指 Canal 收到这个 Binlog，构造为自己协议对象的时间
     * 应用消费的延迟 = now - ts
     */
    private Long ts;

    /**
     * INSERT（新增）、UPDATE（更新）、DELETE（删除）等等
     */
    private String type;
}
