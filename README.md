# SyncUp

ACTIONS

Friends
	-Find by phone # or email
	-Add/Remove

Profile
	-Edit pojo

Events
	-Update event
	-Update Confirmation
	-Check for updates





Firebase DB  
	-PhoneNumbers  
		-<Phone Number>  
			-Private  
				-EventList  
			-Public  
				-DisplayName  
				-PictureSmall  
				-NotificationSetting  
			-Updates  
				-(unimportant updates [what is unimportant is determined by NotificationSetting] are pushed here. This field is checked when the app is opened, and new information is retrieved)  
				-NameLocDateTime:bool  
				-Picture:bool  
				-NoteList:bool  
				-Invitations:bool  
	-Events  
		-<EventId>  
			-Name  
			-PictureMedium (this is compressed locally, but only one version is stored in FB)  
			-Location  
			-FromTime  
			-ToTime  
			-NoteList  
				-(all notes for the Event are pushed here)  
			-Invitations  
				-(list of user:status)  


Shared Preferences  
	-CurrentEventId  
	-UserPhoneNumber  


SQLite3 DB  
	-TableEvent  
		-EventId : Integer (primary key)  
		-Name : String  
		-PictureMedium : String  
		-Location : String  
		-FromTime : String (time)  
		-ToTime : String (time)  
	-TableNote  
		-NoteId : Integer (primary key)  
		-EventId : Integer  
		-Note : String  
	-TableInvitation  
		-InvitationId : Integer (primary key)  
		-EventId : Integer  
		-UserPhone : Integer  
	-TableUser  
		-UserPhone : Integer (primary key)  
		-DisplayName : String  
		-PictureSmall : String  
		-TimeZone : String  



UI shows information pulled from the local db.  Local db is updated when the app is opened or upon user request from FB.  Only the parts of an event that have been updated are downloaded (primarily to prevent unecessary picture downloads).  If a user cannot access the FB db when the app is opened, they are notified that the app may be out of date and are prevented from making changes.




Add Event:  
	-Event object with temporary id of 0 is created  
	-Object is added to SQL  
	-Object is pushed to FB, receives permentant ID  
	-SQL id is updated  
	-Time:  
		-Times are stored as a milisecond long. This is converted to gregorian on the fly  
Edit Event:  
	-Changes are saved in SQL and FB  
	-All subscribed users have updates added to FB.Users.Updates.<type>  
Sync With FB (when app starts or on request):  
	-Pull down Updates object from FB.<me>.Updates  
	-Pull down anything listed as having been updated  
Populate Event List:  
	-List is populated from SQL  




