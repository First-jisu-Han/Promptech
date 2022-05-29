package com.DBproject.DBproject.Repository;

import com.DBproject.DBproject.controller.dto.SumCostDto;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }

    // 프로젝트 투입 내역 저장 - 기존에 employee,project는 등록이 되어있는상황- merge를 써야함 persist x
    public void saveWorkingProject(Works_for inputProject){
        em.merge(inputProject);
    }

    public List<Project> findProjectListByName(String project_name) {
        List<Project> jpql=em.createQuery("select p from Project p where p.project_name like :p_name", Project.class)
                .setParameter("p_name", "%"+project_name+"%")
                .getResultList();
        return  jpql;
    }

    public List<Project> findById(String id){
        return em.createQuery("select p from Project p where p.project_id = :p_id", Project.class)
                .setParameter("p_id",id)
                .getResultList();
    }



        public List<Works_for> findProjectByName(String project_name){
            List<Works_for> jpql=em.createQuery("select w from Works_for w where w.project.project_name = :name",Works_for.class)
                    .setParameter("name", project_name)
                    .getResultList();
            return jpql;
        }

    public Project findByProjcetId(String id){
        return em.find(Project.class,id);
    }


    // 진행 중인 프로젝트 상황 가져오기
    public List<Works_for> findDoingProjectsInfoAll(){
        return em.createQuery("select w from Works_for w",Works_for.class)
                .getResultList();
    }

    public List<Project> findAll(){
        return em.createQuery("select distinct p from Project p left join fetch p.works_fors order by p.end_date desc ", Project.class)
                .getResultList();
    }

    public List<Works_for> findEmployeeById(int employeeId,String projectId) {
        return em.createQuery("select w from Works_for w join fetch Project p on p.project_id=w.project.project_id join fetch Employee e on w.employee.employee_id=e.employee_id" +
                "  where w.employee.employee_id=:e_id and w.project.project_id=:p_id", Works_for.class)
                .setParameter("e_id", employeeId)
                .setParameter("p_id",projectId)
                .getResultList();
    }

    public List<Works_for> findProjectById(String id) {
        return em.createQuery("select w from Works_for  w where w.project.project_id=:id", Works_for.class)
                .setParameter("id", id)
                .getResultList();

    }

    public List<SumCostDto> sumProjectCost(){
        return em.createQuery("select  new com.DBproject.DBproject.controller.dto.SumCostDto( sum (p.project_cost)) from Project p where p.end_date >= CURRENT_DATE",SumCostDto.class)
                .getResultList();
    }


}