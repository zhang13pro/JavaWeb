package vip.itellyou.util.base;

import java.util.List;

/**
 * ���ݷ�����ĸ��ӿ�
 *   ����ͨ�õ���ɾ�Ĳ�ķ���
 * @author Ma Wenhai
 *
 */
public interface BaseDao {
	//��
	//int:��������ֵ��������������
	//Object data:Ӧ�����ϴ���ԭ��
	public int insert(Object data) throws Exception;
	//�����ж���id���Զ������ģ���Ϊ����
	//������������û��idֵ���޸�ʱ�������б�����idֵ
	public int update(Object data) throws Exception;
	//����id��ɾ��
	public int delete(int id) throws Exception;
	
	//��д��ѯ����
	//����е����м�¼
	public List findAll() throws Exception;
	//����id������һ������
	public Object findOne(int id) throws Exception;
	//�������������������������ļ�¼
	//�������������ݣ�һ��������ĳЩ���Ե�ֵ��ʵ����
	//UserQueryModel  SubjectQueryModel
	public List findByCondition(BaseQueryModel queryModel) throws Exception;
	
	
	public Long findId() throws Exception;
	
	
	
}
