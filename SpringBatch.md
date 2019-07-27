# Spring Batch

## 基本概念
- Job Repository：作业仓库，负责Job、Step执行过程中的状态保存
- Job Launcher：作业调度器，提供执行Job的入口
- Job：作业，由多个Step组成，封装整个批处理操作
- Step：作业步，Job的一个执行环节，一或多个Step组成一个Job
- Tasklet：Step中具体执行逻辑的操作，可以重复执行，可以设置具体的同步、异步操作等
- Chunk：给定数量Item的集合，可以定义对Chunk的读操作、处理操作、写操作，提交间隔等
- Item：一条数据记录
- ItemReader：从数据源（文件系统、数据库、队列等）中读取Item
- ItemProcessor：在Item写入数据源之前，对数据进行处理（如数据清洗、数据转换、数据过滤、数据校验等）
- ItemWriter：将Item批量写入数据源（文件系统、数据库、队列等）

## 表结构
- BATCH_JOB_INSTANCE: 作业实例表，存放Job实例信息
- BATCH_JOB_EXECUTION_PARAMS: 作业参数表，用于存放每个Job实例执行时候的参数信息
- BATCH_JOB_EXECUTION: 作业执行器表，用于存放当前作业的执行信息，比如创建时间、执行时间、job实例、执行状态等
- BATCH_JOB_EXECUTION_CONTEXT: 作业执行上下文表，用于存放作业执行器上下文信息
- BATCH_STEP_EXECUTION: 作业步执行器表，用于存放每个Step执行的信息，如开始、完成时间，执行状态，读写次数等
- BATCH_STEP_EXECUTION_CONTEXT: 作业步执行上下文表，用于存放没法作业步上下文信息
- BATCH_JOB_SEQ: 给表BATCH_JOB_INSTANCE、BATCH_JOB_EXECUTION_PARAMS提供主键
- BATCH_JOB_EXECUTION_SEQ: 为表BATCH_JOB_EXECUTION、BATCH_JOB_EXECUTION_CONTEXT提供主键
- BATCH_STEP_EXECUTION_SEQ: 为表BATCH_STEP_EXECUTION、BATCH_STEP_EXECUTION_CONTEXT提供主键

## Job
批处理作业由一组Step组成，同时是作业配置文件的顶层元素，每个作业由自己的名字、可以定义Step执行的顺序，以及定义作业是否可以重启。
### Job Instance
Job执行的时候会生成一个Job Instance，其包含了执行Job期间产生的数据以及Job执行的状态信息等。Job Instance通过JobName和JobParameter来彼此区分。  
一个Job可能有一到多个Job Instance，一个Job Instance可能有一到多个Job Execution。  
Job Instance是一个运行时的概念，Job每运行一次都会涉及一个Job Instance。Job Instance来源可能有两种：根据设置的Job Parameter从Job Repository中获取一个；如果没有则新创建一个Job Instance。  
总结：  
- 第一次执行Job的时候，会创建一个新的Job Instance和新的Job Execution
- 每次执行Job的时候，都会创建一个新的Job Execution
- 已经完成的Job Instance，不能被重新执行
- 在同一时刻，只能有一个Job Execution可以执行同一个Job Instance

### Job Parameter
Job通过Job Name和Job Parameter来区分不同的Job Instance。Job Parameter共支持四种类型的参数：String，Date，Long，Double。  

### Job Execution
Job Execution表示Job执行的句柄，一次Job的执行可能成功也可能失败，只有Job Execution执行成功后，对应的Job Instance才会被完成。  

### Job Listener
Spring Batch在Job执行阶段提供了拦截器，Listener的执行顺序为ListenerA.beforeJob，ListenerB.beforeJob，... ，ListenerB.afterJob，ListenerA.afterJob。  

### Job Explorer
JobExplorer主要负责从JobRepository中获取执行的信息，包括获取作业实例、获取作业执行器、获取作业步执行器、获取正在运行的作业执行器、获取作业列表等操作。  

### Job Launcher
JobLauncher提供运行Job的能力，通过给定的Job名称和作业参数，执行Job。  

### Job Operator
JobOperator包含了JobLauncher和JobExplorer中的大部分操作，包括从JobRepository中查询作业状态信息，同时包含执行Job的操作。  


## Step
Step表示作业中的一个完整步骤，一个Job可以由一个或多个Step组成。Step包含了一个实际运行的批处理任务中所有必须的信息。每个Step由tasklet元素描述具体的作业，tasklet可以是一个自定义的业务处理，也可以使用chunk元素描述面向块的业务处理，一个典型的chunk包含read、process和write三个操作。  

### 分区Step
分区作业可以分为两个阶段：数据分区和分区处理。  
数据分区：根据特定的规则将数据进行合理的切片，为不同的切片生成数据执行上下文ExecutionContext、作业步执行器Step Execution。可以通过Partitioner生成自定义的分区逻辑。  
分区处理：通过数据分区后，不同的数据被分配到不同的作业步执行器中，接下来就需要交给分区处理器处理。PartitionHandler接口定义了分区处理的逻辑。  

#### 关键接口
Spring Batch分区处理中包含三个核心接口：PartitionHandler、StepExecutionSplitter、Partitioner。Partitioner接口定义了根据数据结构将作业进行分区，生成执行上下文。StepExecutionSplitter根据给定Partitioner产生的而执行上线文生成作业步执行器，然后交个PartitionHandler来处理。  


### StepExecutionListener
Spring Batch在Step执行阶段提供了拦截器。Listener的执行顺序同JobListener。  

### Tasklet
tasklet元素定义任务的具体执行逻辑，执行逻辑可以自定义实现，也可以使用Chunk操作，其提供了标准的读、处理、写三步操作。通过tasklet元素同样可以定义事务、处理线程、启动控制、回滚控制、拦截器等功能。  
