
# pr -> Price, pro -> Product, qn -> Quantity

# These functions are just following the discount rule 
def flat_10_discount(total_pr):
    if (total_pr > 200): return total_pr-10
    return total_pr
def bulk_10_discount(total_qn,total_pr):
    if(total_qn > 20): return total_pr - (total_pr*0.1)
    return total_pr
def discount(total_pr,qn,cost,percent):
    item_pr = qn*cost
    total_pr -= (item_pr*percent)  
    return total_pr

#hr@zennode.com
#Printing the item and their prices
product_list = {"Chocolate":25,"Chips":20,"Candy":10,}#"KinderJoy":45,"Coke":50}
print("Product-Name: \t\t Price") 
for pro in product_list: print(f" {pro: <25} {product_list[pro]}")
print("\n")


shop = {}
gift = inp = 0
total_pr = total_qn = 0
print("Enter quantity:","*NOTE: Gift wrap fee: 1 per unit",sep="\n")
for pro in product_list:
    while(1):
        try:
            inp = int(input(" "+pro+":")) # taking user-input until correct format appears
            shop[pro] = inp;
            if(inp<0):print(" Please Enter a Existing quantity!.");continue;
        except:print(" Please Enter a Valid Quantity!.");continue
        break
    while(1):
        inp = input(f" {pro} Include Gift Wrap? (Y/N):")
        if(inp=="Y" or inp=="y"): gift+=shop[pro];break
        elif(inp=="N" or inp=="n"): break
        else: print(" Please Enter \"Y/y\" or \"N/n\".");continue    
    total_pr += shop[pro]*product_list[pro]
    total_qn += shop[pro]


discount_price = total_pr
discount_offer = ""
# calculating the discount amount for the most beneficial one for customer
price = flat_10_discount(total_pr)
if(discount_price>price):
    discount_price = price
    discount_offer = "flat_10_discount"

price = bulk_10_discount(total_qn,total_pr)
if(discount_price>price):
    discount_price = price
    discount_offer = "bulk_10_discount"

for pro in shop:
    if(shop[pro]>10):
        price = discount(total_pr,shop[pro],product_list[pro],0.05)
        if(discount_price>price):
            discount_price = price
            discount_offer = "bulk_5_discount"
    if(total_qn>30 and shop[pro]>15):
        price = discount(total_pr,shop[pro]-15,product_list[pro],0.5)
        if(discount_price>price):
            discount_price = price
            discount_offer = "tiered_50_discount"
print("\n")
ship_pr = (total_qn//10)*5
if(total_qn%10 > 0): ship_pr+=5


#Printing the bill/Output
print("Billing:-")
print("Product-Name:\t\t Quantity\t  Amount") 
for pro in shop: print(f" {pro: <25} {shop[pro]: <15} {shop[pro]*product_list[pro]}")
#print("\n")
print("Subtotal:",total_pr)
print(f"Discount Applied \"{discount_offer}\" ✨✨",f"The Discount amount: {discount_price}",sep="\n")
print(f"The shipping fee: {ship_pr}",f"The gift wrap fee:{gift}",sep="\n")
print("Total:",discount_price+ship_pr+gift)

if(input("")):pass
#print(product_list,shop,total_pr,total_qn,gift)

