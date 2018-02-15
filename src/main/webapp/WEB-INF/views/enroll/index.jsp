<%@include file="../shared/header.jsp" %>
    <h1>Enroll</h1>
    <form method="post" action="">
        <div class="form-group">
            <label>First name</label>
            <input type="text" name="first_name" class="form-control" />
        </div>
        <div class="form-group">
            <label>Last name</label>
            <input type="text" name="last_name" class="form-control" />
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" class="form-control" />
        </div>
        <div class="form-group">
            <label>Contact no</label>
            <input type="text" name="contact_no" class="form-control" />
        </div>
        <div class="form-group">
            <label>Course</label>
            <select name="course_id" class="form-control">
                <option value="">Select Course</option>
                <c:forEach var="course" items="${requestScope.courses}">
                     <option value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>
<%@include file="../shared/footer.jsp" %>