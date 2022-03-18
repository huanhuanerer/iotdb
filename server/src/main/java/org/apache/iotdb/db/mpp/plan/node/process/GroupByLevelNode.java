/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.db.mpp.plan.node.process;

import org.apache.iotdb.db.mpp.plan.node.PlanNodeId;

/**
 * This node is responsible for the final aggregation merge operation. It will process the data from
 * TsBlock row by row. For one row, it will rollup the fields which have the same aggregate function
 * and belong to one bucket. Here, that two columns belong to one bucket means the partial paths of
 * device after rolling up in specific level are the same. For example, let's say there are two
 * columns `root.sg.d1.s1` and `root.sg.d2.s1`. If the group by level parameter is [0, 1], then
 * these two columns will belong to one bucket and the bucket name is `root.sg.*.s1`. If the group
 * by level parameter is [0, 2], then these two columns will not belong to one bucket. And the total
 * buckets are `root.*.d1.s1` and `root.*.d2.s1`
 */
public class GroupByLevelNode extends ProcessNode {

  private int[] groupByLevels;

  public GroupByLevelNode(PlanNodeId id, int[] groupByLevels) {
    super(id);
    this.groupByLevels = groupByLevels;
  }
}