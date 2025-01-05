# Inferno_YouTube_Filtering
Application to generate filtered search queries that you can copy and paste into the search bar.

# What is this?
This is a generator for YouTubes hidden advanced search feature. Basically it makes searching for what you want much more easier by creating a search query that will match what you want when you copy and paste it into the search bar.

# How to use
All you have to do is type a keyword, phrase, or whatever, then choose how you want the result to come out (in the title, in the description, etc) and click the "Print" button to generate what you want.

![image](https://github.com/user-attachments/assets/462fefee-a50c-4bf5-b38d-e69eaec6d9e2)

## Typing your query
Under the "Enter what you're searching" just type how you normally would on YouTube's search bar. You have the option to only search for one thing or search for multiple things by typing 2 equal signs "=="

![image](https://github.com/user-attachments/assets/df02d638-c649-43f5-82e9-85f771d506d3)

## Choosing how you want your query to be searched
Radio buttons will be found below where you searched. that look like this:

![image](https://github.com/user-attachments/assets/f7ee7739-55d8-444c-bef0-984e415a8ee9)

Here's what each one does and what they mean:
- "Exact Value" = Creates a query result where the exact words should be present in your searches. It doesn't matter where (title, description, title, whatever)
- "In-title" = Creates a query result where whatever you typed is found in the title when you search on YouTube. Difference with "Exact Value" is that this only gets videos with these exact words in the title instead of anywhere else.
- "In-description" = The same as "In-title" but this time searches for videos what have the exact word in the description.
- "In-tag" = the same as the previous 2 but finds videos that have the exact word as a tag.
- "None" = The Default, This creates a regular query as if searching on YouTube regularly. The caveat is that if you are using the "==" symbol to create a query for more than one thing then YouTube should search for videos for all of those.

# Dates and Formats
You can even choose what time period the video you are searching for is.

## Dates
Dates are selected by choosing either a start date (after), an end date (before) or both to make your searches more refined. 

Example of using Start:
![image](https://github.com/user-attachments/assets/21a1a95b-8ee4-4744-9608-b3158fe8cc01)

Example of using End:
![image](https://github.com/user-attachments/assets/791c6301-b43f-434d-8f7d-ee6722adb1d9)

Example of using both:
![image](https://github.com/user-attachments/assets/b443823b-5910-4b72-8f50-e52bf13033ce)

Note: Due to the size the words cut off. I will address this is a later version when I have time.

## Date Formats
Dates can be typed manually or using the datepicker (As seen below).

![image](https://github.com/user-attachments/assets/bce6990f-b398-443c-9bf6-56f1e19a0f90)


When using the date picker. Make sure that the first format `MM{-/}DD{-/}YYYY` from the format section is selected.

![image](https://github.com/user-attachments/assets/379c826f-b682-462c-b08a-7449c207eea2)


When manually typing a date make sure you are aware of the chosen format as you will have to conform to that when typing.

## Types of Date Formats and More
When choosing a format, that will be what you will be how you have to type the date so make sure you know what you're doing.

Available Formats for dates and Example.
1. `MM{-/}DD{-/}YYYY` = **02-10-2025** or **02/10/2025** or **2-10-25**
2. `DD{-/}MM{-/}YYYY` = **16-07-2025** or **16/07/2025** or **16-7-25**
3. `YYYY{-/}MM{-/}DD` = **2025-10-13** or **2025/10/13** or *25-10-13**

Note: `{-/}` means that you can use either '-' or '/' when typing a date.

Another thing to keep in mind is that when typing months and days you do not have to include the leading 0 if the month or day is 1 through 9. So 09 can be 9 and 01 can be 1. 
Something similar can be done with the year. If the year is 2025 then you can just type 25 and if the year is 2006 then you can type 06.

> [!NOTE]  
> Note that the date section is disabled by default and you have to manually enable it to use it.
