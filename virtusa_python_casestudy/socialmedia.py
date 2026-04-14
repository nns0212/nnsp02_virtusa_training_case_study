# Sample posts
posts = [
    ("User123", "I hate this app http://badlink.com"),
    ("User456", "This is a good post"),
    ("User123", "toxic content here http://test.com"),
    ("User789", "Hello world"),
]

banned_words = ["bad", "toxic", "hate"]

links = []
user_flags = {}
cleaned_posts = []
total = len(posts)
cleaned = 0
blocked = 0

for user, text in posts:
    original_text = text
    flagged = False

    for word in banned_words:
        if word in text.lower():
            text = text.replace(word, "***")
            flagged = True

    words = text.split()
    for w in words:
        if w.startswith("http"):
            links.append(w)

    if user not in user_flags:
        user_flags[user] = 0
    if flagged:
        user_flags[user] += 1
        cleaned += 1
    else:
        blocked += 1

    cleaned_posts.append((user, text))

with open("links_found.txt", "w") as f:
    for link in links:
        f.write(link + "\n")

print("---- Cleaned Posts ----")
for user, text in cleaned_posts:
    print(user + ":", text)

print("\n---- User Flags ----")
print(user_flags)

print("\n---- Report ----")
print(f"Total Posts Screened: {total} | Cleaned: {cleaned} | Blocked: {blocked}")