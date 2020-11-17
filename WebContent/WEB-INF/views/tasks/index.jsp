<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:param name="content">
  <h2>メッセージ一覧</h2>
        <ul>
        <c:forEach var="task" items="${tasks}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${task.id}">
                        <c:out value="${task.id}" />
                    </a>
 ：<c:out value="${task.content}"/>:作成日時<fmt:formatDate value="${task.created_at}" pattern="yyyy-MM-dd HH:mm:ss"  />

  </li>
     </c:forEach>
     </ul>

      <p><a href="${pageContext.request.contextPath}/new">新規コンテンツの投稿</a></p>
 </c:param>
 </c:import>