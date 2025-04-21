// 初始化
document.addEventListener('DOMContentLoaded', () => {
    loadCatalog();
    refreshOrder();
});

// 加载产品目录
async function loadCatalog() {
    const res = await fetch('/api/products');
    const products = await res.json();

    const catalogDiv = document.getElementById('catalog');
    catalogDiv.innerHTML = products.map(p => `
        <div class="product-card">
            <h3>${p.code} - ${p.description}</h3>
            <p>Price: $${p.price.toFixed(2)}</p>
        </div>
    `).join('');
}

// 添加商品到订单
async function addToOrder() {
    const code = document.getElementById('product-code').value;
    const quantity = parseInt(document.getElementById('quantity').value);

    const response = await fetch('/api/order/items', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code, quantity })
    });

    if (response.ok) refreshOrder();
}

// 刷新订单显示
async function refreshOrder() {
    const res = await fetch('/api/order');
    const items = await res.json();

    const orderDiv = document.getElementById('order-items');
    orderDiv.innerHTML = items.map(item => `
        <div class="order-item">
            ${item.quantity}x ${item.code}
            <span class="price">$${(item.quantity * item.price).toFixed(2)}</span>
            <button onclick="removeItem('${item.code}')">x</button>
        </div>
    `).join('');

    // 计算ans
    const total = items.reduce((sum, item) => sum + (item.quantity * item.price), 0);
    document.getElementById('order-total').textContent = total.toFixed(2);
}

// 打印
async function completeSale() {
    const response = await fetch('/api/sales', { method: 'POST' });
    if (response.ok) {
        alert('Sale completed!');
        refreshOrder();
    }
}
