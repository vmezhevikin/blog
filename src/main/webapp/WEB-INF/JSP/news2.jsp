<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<c:forEach var="category" items="${categories}">
		<p class="text-info">${category.id} == ${category.name} === ${category.count}</p>
	</c:forEach>
	<table class="table">
		<c:forEach var="article" items="${articles}">
			<tr>
				<td>
					<img src="${article.image}" alt="img"/>
				</td>
			</tr>
			<tr>
				<td>
					<p class="text-success">${article.author.id}</p>
					<p class="text-success">${article.author.name}</p>
					<p class="text-success">${article.author.email}</p>
					<p class="text-success">${article.author.password}</p>
				</td>
			</tr>
			<tr>
				<td>
					<p class="text-success">${article.id}</p>
					<p class="text-success">${article.date}</p>
					<p class="text-success">${article.category.name}</p>
					<p class="text-success">${article.name}</p>
					<p class="text-success">${article.description}</p>
					<p class="text-success">${article.text}</p>
				</td>
			</tr>
			<tr>
				<td>
					<c:forEach var="comment" items="${article.comments}">
						<img src="${comment.author.image}" alt="img"/>
						<p class="text-warning">${comment.date}</p>
						<p class="text-warning">${comment.text}</p>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>			
	</table>
	<table class="table">
		<c:forEach var="author" items="${authors}">
			<tr>
				<td>
					<span class="text-danger">${author.id}</span>
				</td>
				<td>
					<span class="text-danger">${author.name}</span>
				</td>
				<td>
					<img src="${author.image}" />
				</td>
				<td>
					<span class="text-danger">${author.email}</span>
				</td>
				<td>
					<span class="text-danger">${author.password}</span>
				</td>
			</tr>
		</c:forEach>			
	</table>
</body>