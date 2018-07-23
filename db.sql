
--�������ֱ�
create table localMuList(
mno int primary key,
mname varchar2 (25)  not null,
singer varchar2 (20) ,
collection varchar2 (20) default 'δ֪ר��',  --ר��
mtime int,                   --ʱ�����룩
msize number(4),            --��С��MB��
filepath varchar2 (50) not null
)
--mno������
create sequence seq_LocalMuList_mno start with 1 increment by 1;

--������ű�
create table recentList(
mno int primary key
constraint FK_mno_recentList references LocalMuList(mno),
frequency number(4) default 0        --���Ŵ���

)

--Lrc����ļ���
create table lrcList(
filepath varchar2 (50) primary key,
mname varchar2 (25) not null

)

--�ҵ��ղ�
create table myCollection(
mname varchar2 (25)  not null,
singer varchar2 (20) ,
collection varchar2 (20) default 'δ֪ר��',  --ר��
mtime int,                   --ʱ�����룩
filepath varchar2 (50) not null
)

--�赥
create table songSheet(
sname varchar2 (25) primary key,
mname varchar2 (25)  not null,
singer varchar2 (20) ,
collection varchar2 (20) default 'δ֪ר��',  --ר��
mtime int,                   --ʱ�����룩
filepath varchar2 (50) not null
)
--���������ļ���
create table fileDirectory(
filepath varchar2 (50) primary key 
)


select * from localMuList
