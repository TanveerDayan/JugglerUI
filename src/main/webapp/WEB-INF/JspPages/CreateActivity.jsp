<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
</style>
<script type="text/javascript">
	$("#idForm").submit(function() {
		var url = "/JugglerUI/createActivity";
		$.ajax({
			type : "POST",
			url : url,
			data : $("#idForm").serialize(),
			success : function(data) {
				$("#childDiv").load("/JugglerUI/activityPage");
			}
		});
		return false;
	});
</script>
<div class="ui form">
	<h3 class="ui dividing header">Create Activity</h3>

	<form:form id="idForm" method="POST">
		<div class="field">
			<form:input path="activityName" placeholder="Name" />
		</div>
		<div class="field">
			<form:input path="activityStartTime" placeholder="Start Time" />
		</div>
		<div class="field">
			<form:input path="activityEndTime" placeholder="End Time" />
		</div>
		<div class="field">
			<form:input path="activityCity" placeholder="City" />
		</div>
		<div class="field">
			<form:input path="activityLocality" placeholder="Locality" />
		</div>
		<div class="field">
			<form:input path="activityAddress" placeholder="Address" />
		</div>
		<div class="field">
			<form:select multiple="false" path="hobbies"
				style="color: rgba(140, 140, 140, .8);">
				<form:option value="Hobbies"></form:option>
				<form:options items="${hobbiesDropDown}" />
			</form:select>
		</div>
		<div class="juggler-float-right">
			<input type="submit" value="Create Activity" class="ui button" />
		</div>
	</form:form>
</div>

